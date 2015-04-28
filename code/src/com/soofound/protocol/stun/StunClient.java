/*   1:    */package com.soofound.protocol.stun;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.io.PrintStream;
/*   7:    */import java.net.DatagramPacket;
/*   8:    */import java.net.InetAddress;
/*   9:    */import java.net.InetSocketAddress;
/*  10:    */import java.net.Socket;
/*  11:    */import java.net.SocketException;
/*  12:    */import java.net.SocketTimeoutException;
/*  13:    */import org.apache.log4j.Logger;
/*  14:    */
/*  15:    */public class StunClient extends Thread
/*  16:    */{
/*  17: 17 */  private static final Logger logger = Logger.getLogger(StunClient.class.getName());
/*  18:    */  
/*  19:    */  private static final int TIMEOUT = 3000;
/*  20:    */  
/*  21:    */  private static final int RETRIES = 3;
/*  22: 22 */  private static int timeout = 3000;
/*  23: 23 */  private static int retries = 3;
/*  24:    */  
/*  25:    */  private InetSocketAddress stunServer;
/*  26:    */  
/*  27:    */  private java.net.DatagramSocket datagramSocket;
/*  28:    */  
/*  29:    */  private Socket socket;
/*  30:    */  private DataInputStream input;
/*  31:    */  private InetSocketAddress mappedAddress;
/*  32:    */  private boolean done;
/*  33:    */  
/*  34:    */  public StunClient(InetSocketAddress stunServer, java.net.DatagramSocket datagramSocket)
/*  35:    */    throws IOException
/*  36:    */  {
/*  37: 37 */    this.stunServer = stunServer;
/*  38: 38 */    this.datagramSocket = datagramSocket;
/*  39: 39 */    logger.info("starting stun client to " + stunServer);
/*  40: 40 */    start();
/*  41:    */  }
/*  42:    */  
/*  44:    */  public StunClient(Socket socket)
/*  45:    */    throws IOException
/*  46:    */  {
/*  47: 47 */    this.socket = socket;
/*  48: 48 */    this.stunServer = new InetSocketAddress(socket.getInetAddress(), socket.getPort());
/*  49: 49 */    this.input = new DataInputStream(socket.getInputStream());
/*  50: 50 */    start();
/*  51:    */  }
/*  52:    */  
/*  54:    */  public InetSocketAddress getMappedAddress()
/*  55:    */    throws IOException
/*  56:    */  {
/*  57: 57 */    synchronized (this) {
/*  58: 58 */      while (!this.done) {
/*  59:    */        try {
/*  60: 60 */          wait();
/*  61:    */        } catch (InterruptedException e) {
/*  62: 62 */          throw new IOException("Failed to retrieve mapped address:  Interruped");
/*  63:    */        }
/*  64:    */      }
/*  65:    */    }
/*  66: 66 */    if (this.mappedAddress == null) {
/*  67: 67 */      String s = "Failed to retrieve mapped address";
/*  68: 68 */      if (this.socket != null) {
/*  69: 69 */        s = s + " for " + this.socket.getLocalAddress() + ":" + this.socket.getLocalPort();
/*  70: 70 */      } else if (this.datagramSocket != null) {
/*  71: 71 */        s = s + " for " + this.datagramSocket.getLocalAddress() + ":" + this.datagramSocket.getLocalPort();
/*  72:    */      }
/*  73: 73 */      logger.debug(s);
/*  74: 74 */      logger.debug("IF YOU ARE BEHIND A FIREWALL OR NAT, ADDRESSES ARE NOT LIKELY TO BE CORRECT!");
/*  75: 75 */      throw new IOException(s);
/*  76:    */    }
/*  77: 77 */    logger.debug("mapped address is " + this.mappedAddress);
/*  78: 78 */    return this.mappedAddress;
/*  79:    */  }
/*  80:    */  
/*  81:    */  private void done() {
/*  82: 82 */    synchronized (this) {
/*  83: 83 */      this.done = true;
/*  84: 84 */      notifyAll();
/*  85:    */    }
/*  86:    */  }
/*  87:    */  
/*  88:    */  public void run()
/*  89:    */  {
/*  90: 90 */    logger.info("using STUN server " + this.stunServer);
/*  91:    */    try {
/*  92: 92 */      if (this.datagramSocket != null) {
/*  93: 93 */        int socketTimeout = this.datagramSocket.getSoTimeout();
/*  94: 94 */        this.datagramSocket.setSoTimeout(timeout);
/*  95:    */      } else {
/*  96: 96 */        int socketTimeout = this.socket.getSoTimeout();
/*  97: 97 */        this.socket.setSoTimeout(timeout);
/*  98:    */      }
/*  99:    */    } catch (SocketException e) {
/* 100:100 */      logger.debug("Unable to set socket timeout:  " + e.getMessage());
/* 101:101 */      done(); return;
/* 102:    */    }
/* 103:    */    
/* 104:    */    int socketTimeout;
/* 105:105 */    for (int i = 0; i < retries; i++) {
/* 106:    */      try {
/* 107:107 */        logger.debug("Sending stun request " + i);
/* 108:108 */        sendStunRequest();
/* 109:109 */        waitForReply();
/* 110:    */      }
/* 111:    */      catch (IOException localIOException) {}
/* 112:    */    }
/* 113:    */    try
/* 114:    */    {
/* 115:115 */      if (this.datagramSocket != null) {
/* 116:116 */        this.datagramSocket.setSoTimeout(socketTimeout);
/* 117:    */      } else {
/* 118:118 */        this.socket.setSoTimeout(socketTimeout);
/* 119:    */      }
/* 120:    */    } catch (SocketException e) {
/* 121:121 */      logger.debug("Unable to reset socket timeout:" + e.getMessage());
/* 122:    */    }
/* 123:123 */    done();
/* 124:    */  }
/* 125:    */  
/* 126:    */  private void sendStunRequest()
/* 127:    */    throws IOException
/* 128:    */  {
/* 129:129 */    this.mappedAddress = null;
/* 130:130 */    int port; InetAddress addressToMap; int port; if (this.datagramSocket != null) {
/* 131:131 */      InetAddress addressToMap = this.datagramSocket.getLocalAddress();
/* 132:132 */      port = this.datagramSocket.getLocalPort();
/* 133:    */    } else {
/* 134:134 */      addressToMap = this.socket.getLocalAddress();
/* 135:135 */      port = this.socket.getLocalPort();
/* 136:    */    }
/* 137:137 */    logger.debug("StunClient:  asking STUN server " + 
/* 138:138 */      this.stunServer.getAddress().getHostAddress() + ":" + 
/* 139:139 */      this.stunServer.getPort() + 
/* 140:140 */      " to get mapping for " + addressToMap.getHostAddress() + 
/* 141:141 */      ":" + port);
/* 142:    */    
/* 143:143 */    byte[] buf = new byte[32];
/* 144:144 */    buf[1] = 1;
/* 145:145 */    buf[3] = 12;
/* 146:146 */    long time = System.currentTimeMillis();
/* 147:147 */    for (int i = 0; i < 16; i++) {
/* 148:148 */      buf[(i + 4)] = ((byte)(int)(time >> i % 4 * 8));
/* 149:    */    }
/* 150:150 */    buf[21] = 1;
/* 151:151 */    buf[23] = 8;
/* 152:152 */    buf[25] = 1;
/* 153:153 */    buf[26] = ((byte)(port >> 8));
/* 154:154 */    buf[27] = ((byte)(port & 0xFF));
/* 155:155 */    byte[] address = addressToMap.getAddress();
/* 156:156 */    buf[28] = address[0];
/* 157:157 */    buf[29] = address[1];
/* 158:158 */    buf[30] = address[2];
/* 159:159 */    buf[31] = address[3];
/* 160:160 */    if (this.datagramSocket != null) {
/* 161:161 */      DatagramPacket packet = new DatagramPacket(buf, buf.length, this.stunServer.getAddress(), this.stunServer.getPort());
/* 162:162 */      logger.debug("local addr " + this.datagramSocket.getLocalAddress() + " local port " + this.datagramSocket.getLocalPort());
/* 163:163 */      this.datagramSocket.send(packet);
/* 164:    */    } else {
/* 165:165 */      DataOutputStream output = new DataOutputStream(this.socket.getOutputStream());
/* 166:166 */      output.write(buf, 0, buf.length);
/* 167:167 */      output.flush();
/* 168:    */    }
/* 169:    */  }
/* 170:    */  
/* 171:    */  private void waitForReply() throws IOException, SocketTimeoutException {
/* 172:172 */    byte[] response = new byte[1000];
/* 173:    */    
/* 181:181 */    for (int i = 0; i < retries; i++) { int length;
/* 182:    */      int length;
/* 183:183 */      if (this.datagramSocket != null) {
/* 184:184 */        DatagramPacket packet = new DatagramPacket(response, response.length);
/* 185:185 */        this.datagramSocket.receive(packet);
/* 186:186 */        length = packet.getLength();
/* 187:    */      } else {
/* 188:188 */        length = this.input.read(response);
/* 189:    */      }
/* 190:190 */      System.out.println("Got response!  " + length + 
/* 191:191 */        " local addr " + this.datagramSocket.getLocalAddress() + 
/* 192:192 */        " local port " + this.datagramSocket.getLocalPort());
/* 193:    */      
/* 194:194 */      int type = response[0] << 8 & 0xFF00 | response[1] & 0xFF;
/* 195:195 */      if (type == 257) {
/* 196:196 */        this.mappedAddress = StunHeader.getAddress(response, 1);
/* 197:197 */        return;
/* 198:    */      }
/* 199:199 */      logger.debug("BAD STUN response, length " + length + " TCP " + (this.input != null));
/* 200:    */    }
/* 201:201 */    throw new IOException("Didn't receive BINDING_RESPONE");
/* 202:    */  }
/* 203:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.stun.StunClient
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */