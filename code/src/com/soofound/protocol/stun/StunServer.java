/*   1:    */package com.soofound.protocol.stun;
/*   2:    */
/*   3:    */import java.io.DataInputStream;
/*   4:    */import java.io.DataOutputStream;
/*   5:    */import java.io.IOException;
/*   6:    */import java.net.DatagramPacket;
/*   7:    */import java.net.DatagramSocket;
/*   8:    */import java.net.InetAddress;
/*   9:    */import java.net.InetSocketAddress;
/*  10:    */import java.net.ServerSocket;
/*  11:    */import java.net.Socket;
/*  12:    */import java.net.SocketException;
/*  13:    */import java.nio.ByteBuffer;
/*  14:    */import java.nio.channels.DatagramChannel;
/*  15:    */import org.apache.log4j.Logger;
/*  16:    */
/*  17:    */public class StunServer
/*  18:    */{
/*  19: 19 */  private static final Logger logger = Logger.getLogger(StunServer.class);
/*  20:    */  private int stunServerPort;
/*  21:    */  private DatagramSocket socket;
/*  22:    */  
/*  23:    */  public StunServer(int stunServerPort) {
/*  24: 24 */    this.stunServerPort = stunServerPort;
/*  25:    */  }
/*  26:    */  
/*  27:    */  public void sendUDP(DatagramPacket packet) throws IOException {
/*  28: 28 */    this.socket.send(packet);
/*  29:    */  }
/*  30:    */  
/*  31:    */  public void startServer() throws IOException {
/*  32: 32 */    new StunUdpListener(this.stunServerPort);
/*  33:    */  }
/*  34:    */  
/*  35:    */  class StunUdpListener extends Thread {
/*  36:    */    private int stunServerPort;
/*  37:    */    
/*  38:    */    public StunUdpListener(int stunServerPort) throws IOException {
/*  39:    */      try {
/*  40: 40 */        StunServer.this.socket = new DatagramSocket(stunServerPort);
/*  41:    */      } catch (SocketException e) {
/*  42: 42 */        throw new IOException("Can't create DatagramSocket:" + e.getMessage());
/*  43:    */      }
/*  44: 44 */      this.stunServerPort = stunServerPort;
/*  45: 45 */      synchronized (this) {
/*  46: 46 */        start();
/*  47:    */        try {
/*  48: 48 */          wait();
/*  49:    */        }
/*  50:    */        catch (InterruptedException localInterruptedException) {}
/*  51:    */      }
/*  52:    */    }
/*  53:    */    
/*  54:    */    public void run() {
/*  55: 55 */      StunServer.logger.debug("STUN Server: Listening for Stun requests on UDP port " + this.stunServerPort + "...");
/*  56: 56 */      synchronized (this) {
/*  57: 57 */        notifyAll();
/*  58:    */      }
/*  59:    */      try {
/*  60:    */        for (;;) {
/*  61: 61 */          byte[] buf = new byte[10000];
/*  62: 62 */          DatagramPacket packet = new DatagramPacket(buf, buf.length);
/*  63: 63 */          StunServer.this.socket.receive(packet);
/*  64: 64 */          StunServer.this.processStunRequest(StunServer.this.socket, packet);
/*  65:    */        }
/*  66: 66 */      } catch (IOException e) { StunServer.logger.error("STUN Server:  send or received failed " + e.toString());
/*  67:    */      }
/*  68:    */    }
/*  69:    */  }
/*  70:    */  
/*  71:    */  class StunTcpListener extends Thread {
/*  72:    */    private ServerSocket serverSocket;
/*  73:    */    private int stunServerPort;
/*  74:    */    
/*  75:    */    public StunTcpListener(int stunServerPort) throws IOException {
/*  76:    */      try {
/*  77: 77 */        this.serverSocket = new ServerSocket(stunServerPort);
/*  78:    */      } catch (SocketException e) {
/*  79: 79 */        throw new IOException("Can't create ServerSocket: " + e.getMessage());
/*  80:    */      }
/*  81: 81 */      this.stunServerPort = stunServerPort;
/*  82: 82 */      synchronized (this) {
/*  83: 83 */        start();
/*  84:    */        try {
/*  85: 85 */          wait();
/*  86:    */        }
/*  87:    */        catch (InterruptedException localInterruptedException) {}
/*  88:    */      }
/*  89:    */    }
/*  90:    */    
/*  91:    */    public void run() {
/*  92: 92 */      StunServer.logger.debug("STUN Server:  Listening for Stun requests on TCP port " + this.stunServerPort + "...");
/*  93: 93 */      synchronized (this) {
/*  94: 94 */        notifyAll();
/*  95:    */      }
/*  96:    */      try {
/*  97:    */        for (;;) {
/*  98: 98 */          Socket socket = this.serverSocket.accept();
/*  99: 99 */          InetAddress address = socket.getInetAddress();
/* 100:100 */          StunServer.logger.debug("New TCP STUN connection accepted from " + address.getHostAddress() + ":" + socket.getPort());
/* 101:101 */          StunServer.this.processStunRequest(socket);
/* 102:    */        }
/* 103:103 */      } catch (IOException e) { StunServer.logger.error("STUN Server:  send or received failed " + e.toString());
/* 104:    */      }
/* 105:    */    }
/* 106:    */  }
/* 107:    */  
/* 112:    */  public void processStunRequest(DatagramChannel channel, InetSocketAddress isa, byte[] request)
/* 113:    */  {
/* 114:114 */    logger.debug("Got UDP Stun request for channel " + request.length + " bytes from " + isa);
/* 115:115 */    byte[] response = getStunResponse(isa, request, request.length);
/* 116:116 */    InetSocketAddress responseAddress = StunHeader.getAddress(request, 2);
/* 117:117 */    if (responseAddress != null) {
/* 118:118 */      isa = new InetSocketAddress(responseAddress.getAddress(), responseAddress.getPort());
/* 119:    */    }
/* 120:120 */    int changeRequest = StunHeader.getChangeRequest(request);
/* 121:121 */    if ((changeRequest & 0x4) != 0)
/* 122:    */    {
/* 127:127 */      return;
/* 128:    */    }
/* 129:129 */    if ((changeRequest & 0x2) == 0) {
/* 130:    */      try {
/* 131:131 */        channel.send(ByteBuffer.wrap(response), isa);
/* 132:    */      } catch (IOException ee) {
/* 133:133 */        logger.error("Can't send Binding Error response on channel: " + ee.getMessage());
/* 134:    */      }
/* 135:135 */      return;
/* 136:    */    }
/* 137:    */    
/* 141:141 */    DatagramSocket responseSocket = null;
/* 142:142 */    String s = null;
/* 143:    */    try {
/* 144:144 */      responseSocket = new DatagramSocket();
/* 145:145 */      DatagramPacket packet = new DatagramPacket(response, response.length, isa);
/* 146:146 */      responseSocket.send(packet);
/* 147:147 */      return;
/* 148:    */    } catch (SocketException e) {
/* 149:149 */      s = "CHANGE_PORT set but can't create new socket! " + e.getMessage();
/* 150:    */    } catch (IOException e) {
/* 151:151 */      s = "Can't send Binding Response on socket: " + e.getMessage();
/* 152:    */    }
/* 153:153 */    logger.debug(s);
/* 154:154 */    response = getBindingErrorResponse(request, 600, s);
/* 155:    */    try {
/* 156:156 */      channel.send(ByteBuffer.wrap(response), isa);
/* 157:    */    } catch (IOException e) {
/* 158:158 */      logger.error("Can't send Binding Error response on channel: " + e.getMessage());
/* 159:159 */      return;
/* 160:    */    }
/* 161:    */  }
/* 162:    */  
/* 166:    */  public void processStunRequest(DatagramSocket socket, DatagramPacket packet)
/* 167:    */  {
/* 168:168 */    byte[] request = packet.getData();
/* 169:169 */    int length = packet.getLength();
/* 170:    */    
/* 171:171 */    InetSocketAddress isa = (InetSocketAddress)packet.getSocketAddress();
/* 172:172 */    logger.debug("Got UDP Stun request on socket " + 
/* 173:173 */      socket.getLocalAddress() + ":" + socket.getLocalPort() + 
/* 174:174 */      " length " + length + " bytes " + " from " + isa);
/* 175:    */    
/* 176:176 */    logger.debug("getRemoteSocketAddress:" + socket.getRemoteSocketAddress());
/* 177:177 */    byte[] response = getStunResponse(isa, request, length);
/* 178:178 */    InetSocketAddress responseAddress = StunHeader.getAddress(request, 2);
/* 179:179 */    if (responseAddress != null) {
/* 180:180 */      packet.setAddress(responseAddress.getAddress());
/* 181:181 */      packet.setPort(responseAddress.getPort());
/* 182:    */    }
/* 183:183 */    int changeRequest = StunHeader.getChangeRequest(request);
/* 184:184 */    if ((changeRequest & 0x4) != 0)
/* 185:    */    {
/* 190:190 */      return;
/* 191:    */    }
/* 192:192 */    DatagramSocket responseSocket = socket;
/* 193:193 */    if ((changeRequest & 0x2) != 0) {
/* 194:    */      try {
/* 195:195 */        responseSocket = new DatagramSocket();
/* 196:    */      } catch (SocketException e) {
/* 197:197 */        String s = "CHANGE_PORT set but can't create new socket! " + e.getMessage();
/* 198:198 */        logger.debug(s);
/* 199:199 */        response = getBindingErrorResponse(request, 600, s);
/* 200:    */      }
/* 201:    */    }
/* 202:202 */    packet.setData(response);
/* 203:    */    try {
/* 204:204 */      responseSocket.send(packet);
/* 205:205 */      String s = "";
/* 206:206 */      if (request.length >= 32) {
/* 207:207 */        int port = request[26] << 
/* 208:208 */          8 & 0xFF00 | request[27] & 0xFF;
/* 209:209 */        String privateAddress = " private address " + (
/* 210:210 */          request[28] & 
/* 211:211 */          0xFF) + "." + (
/* 212:212 */          request[29] & 
/* 213:213 */          0xFF) + "." + (
/* 214:214 */          request[30] & 
/* 215:215 */          0xFF) + "." + (
/* 216:216 */          request[31] & 
/* 217:217 */          0xFF);
/* 218:218 */        s = privateAddress + ":" + port;
/* 219:    */      }
/* 220:220 */      logger.debug("Sent STUN Binding Response from " + 
/* 221:221 */        responseSocket.getLocalAddress() + ":" + 
/* 222:222 */        responseSocket.getLocalPort() + 
/* 223:223 */        " to " + packet.getAddress() + ":" + packet.getPort() + s);
/* 224:    */    } catch (IOException e) {
/* 225:225 */      logger.error("Unable to send STUN response! " + e.getMessage());
/* 226:    */    }
/* 227:    */  }
/* 228:    */  
/* 229:    */  public void processStunRequest(Socket socket) {
/* 230:230 */    logger.debug("Got TCP Stun request from " + socket.getInetAddress() + ":" + socket.getPort());
/* 231:    */    try {
/* 232:232 */      DataInputStream input = new DataInputStream(
/* 233:233 */        socket.getInputStream());
/* 234:234 */      DataOutputStream output = new DataOutputStream(socket.getOutputStream());
/* 235:235 */      InetSocketAddress isa = new InetSocketAddress(
/* 236:236 */        socket.getInetAddress(), socket.getPort());
/* 237:237 */      byte[] request = new byte[1000];
/* 238:238 */      int length = input.read(request);
/* 239:239 */      if (length == -1) {
/* 240:240 */        logger.info("Stunserver socket closed to " + isa);
/* 241:241 */        return;
/* 242:    */      }
/* 243:243 */      byte[] response = getStunResponse(isa, request, length);
/* 244:244 */      output.write(response);
/* 245:245 */      logger.debug("Sent TCP STUN Binding Response to " + isa);
/* 246:    */    } catch (IOException e) {
/* 247:247 */      logger.error("Unable to send TCP STUN response! " + e.getMessage());
/* 248:    */    }
/* 249:    */  }
/* 250:    */  
/* 251:    */  private byte[] getStunResponse(InetSocketAddress isa, byte[] request, int length) {
/* 252:252 */    if (length < 20) {
/* 253:253 */      String msg = "Too short to have STUN HEADER " + length;
/* 254:254 */      logger.debug(msg);
/* 255:255 */      return getBindingErrorResponse(request, 400, msg);
/* 256:    */    }
/* 257:257 */    int messageType = request[0] << 8 & 0xFF00 | request[1] & 0xFF;
/* 258:258 */    if (messageType != 1) {
/* 259:259 */      String msg = "Only Binding Request is supported";
/* 260:260 */      return getBindingErrorResponse(request, 600, msg);
/* 261:    */    }
/* 262:262 */    return processBindingRequest(isa, request, length);
/* 263:    */  }
/* 264:    */  
/* 265:    */  private byte[] processBindingRequest(InetSocketAddress isa, byte[] request, int length) {
/* 266:266 */    byte[] response = new byte[44];
/* 267:    */    
/* 270:270 */    System.arraycopy(request, 0, response, 0, 20);
/* 271:271 */    response[0] = 1;
/* 272:272 */    response[3] = 24;
/* 273:273 */    response[21] = 1;
/* 274:274 */    response[23] = 8;
/* 275:275 */    response[25] = 1;
/* 276:276 */    logger.debug("responding with " + isa);
/* 277:    */    
/* 278:278 */    int sourcePort = isa.getPort();
/* 279:279 */    response[26] = ((byte)(sourcePort >> 8));
/* 280:280 */    response[27] = ((byte)(sourcePort & 0xFF));
/* 281:281 */    byte[] sourceAddress = isa.getAddress().getAddress();
/* 282:282 */    response[28] = sourceAddress[0];
/* 283:283 */    response[29] = sourceAddress[1];
/* 284:284 */    response[30] = sourceAddress[2];
/* 285:285 */    response[31] = sourceAddress[3];
/* 286:    */    
/* 287:287 */    response[33] = 5;
/* 288:288 */    response[35] = 8;
/* 289:289 */    response[37] = 1;
/* 290:290 */    response[38] = ((byte)(sourcePort >> 8));
/* 291:291 */    response[39] = ((byte)(sourcePort & 0xFF));
/* 292:292 */    response[40] = sourceAddress[0];
/* 293:293 */    response[41] = sourceAddress[1];
/* 294:294 */    response[42] = sourceAddress[2];
/* 295:295 */    response[43] = sourceAddress[3];
/* 296:    */    
/* 297:297 */    return response;
/* 298:    */  }
/* 299:    */  
/* 300:    */  private byte[] getBindingErrorResponse(byte[] request, int responseCode, String reason) {
/* 301:301 */    byte[] response = new byte[24 + reason.length()];
/* 302:302 */    System.arraycopy(request, 0, response, 0, 20);
/* 303:303 */    response[0] = 1;
/* 304:304 */    response[1] = 17;
/* 305:    */    
/* 306:306 */    response[22] = ((byte)(responseCode >> 8));
/* 307:307 */    response[23] = ((byte)(responseCode & 0xFF));
/* 308:308 */    byte[] reasonBytes = reason.getBytes();
/* 309:309 */    System.arraycopy(reasonBytes, 0, response, 24, reasonBytes.length);
/* 310:310 */    int length = 24 + reasonBytes.length;
/* 311:311 */    response[2] = ((byte)(length >> 8));
/* 312:312 */    response[3] = ((byte)(length & 0xFF));
/* 313:313 */    return response;
/* 314:    */  }
/* 315:    */  
/* 316:    */  public int getStunServerPort() {
/* 317:317 */    return this.stunServerPort;
/* 318:    */  }
/* 319:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.stun.StunServer
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */