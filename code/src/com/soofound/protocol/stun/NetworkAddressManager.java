/*   1:    */package com.soofound.protocol.stun;
/*   2:    */
/*   3:    */import java.io.IOException;
/*   4:    */import java.net.DatagramSocket;
/*   5:    */import java.net.Inet4Address;
/*   6:    */import java.net.InetAddress;
/*   7:    */import java.net.InetSocketAddress;
/*   8:    */import java.net.NetworkInterface;
/*   9:    */import java.net.Socket;
/*  10:    */import java.net.UnknownHostException;
/*  11:    */import java.util.Enumeration;
/*  12:    */import org.apache.log4j.Logger;
/*  13:    */
/*  14:    */public class NetworkAddressManager
/*  15:    */{
/*  16: 16 */  private static final Logger logger = Logger.getLogger(NetworkAddressManager.class.getName());
/*  17:    */  private static InetAddress privateLocalHost;
/*  18:    */  private String stunServer;
/*  19:    */  private int stunServerPort;
/*  20: 20 */  private static int timeout = 100;
/*  21:    */  
/*  22:    */  public NetworkAddressManager() {}
/*  23:    */  
/*  24:    */  public NetworkAddressManager(String stunServer, int stunServerPort) throws IOException
/*  25:    */  {
/*  26:    */    try {
/*  27: 27 */      stunServer = InetAddress.getByName(stunServer).getHostAddress();
/*  28:    */    } catch (UnknownHostException e) {
/*  29: 29 */      logger.error("Invalid stunServer:" + e.getMessage());
/*  30: 30 */      throw new IOException("Invalid stunServer:  " + e.getMessage());
/*  31:    */    }
/*  32: 32 */    this.stunServer = stunServer;
/*  33: 33 */    this.stunServerPort = stunServerPort;
/*  34:    */  }
/*  35:    */  
/*  36:    */  public void getLocalHost()
/*  37:    */    throws IOException
/*  38:    */  {
/*  39:    */    try
/*  40:    */    {
/*  41: 41 */      privateLocalHost = getLocalHostFromStun();
/*  42: 42 */      logger.info("Using local address " + 
/*  43: 43 */        privateLocalHost.getHostAddress() + 
/*  44: 44 */        " as determined by connecting to " + this.stunServer + 
/*  45: 45 */        ":" + this.stunServerPort);
/*  46: 46 */      showDefaultAddress(false);
/*  47: 47 */      return;
/*  48:    */    }
/*  49:    */    catch (IOException localIOException1) {
/*  50:    */      try {
/*  51: 51 */        privateLocalHost = getLocalHostFromInterfaces();
/*  52:    */        
/*  53: 53 */        logger.info("Using local address " + 
/*  54: 54 */          privateLocalHost.getHostAddress() + 
/*  55: 55 */          " selected from the list of interfaces");
/*  56:    */      } catch (IOException e) {
/*  57: 57 */        logger.info(e.getMessage());
/*  58:    */      }
/*  59:    */    }
/*  60:    */  }
/*  61:    */  
/*  64:    */  public void showDefaultAddress(boolean useStun)
/*  65:    */  {
/*  66: 66 */    InetAddress defaultAddress = null;
/*  67: 67 */    if (useStun) {
/*  68:    */      try {
/*  69: 69 */        defaultAddress = getLocalHostFromStun();
/*  70: 70 */        logger.info("If localHost had not been specified, " + 
/*  71: 71 */          defaultAddress.getHostAddress() + 
/*  72: 72 */          " would have been chosen by using STUN.");
/*  73:    */      }
/*  74:    */      catch (IOException localIOException1) {}
/*  75:    */    }
/*  76: 76 */    if (defaultAddress == null) {
/*  77:    */      try {
/*  78: 78 */        defaultAddress = getLocalHostFromInterfaces();
/*  79: 79 */        logger.info("If localHost had not been specified and could not be determined by using STUN, " + 
/*  80:    */        
/*  81: 81 */          defaultAddress.getHostAddress() + 
/*  82: 82 */          " would have been chosen from the interface list.");
/*  83:    */      } catch (IOException e) {
/*  84: 84 */        logger.info("If localHost had not been specified  it would not have been able to determine local host!");
/*  85:    */      }
/*  86:    */    }
/*  87:    */  }
/*  88:    */  
/*  91:    */  public InetAddress getLocalHostFromStun()
/*  92:    */    throws IOException
/*  93:    */  {
/*  94: 94 */    if (this.stunServer == null) {
/*  95: 95 */      throw new IOException("No Stun Server specified");
/*  96:    */    }
/*  97: 97 */    Socket socket = new Socket();
/*  98: 98 */    InetSocketAddress isa = new InetSocketAddress(this.stunServer, this.stunServerPort);
/*  99: 99 */    socket.connect(isa, 10000);
/* 100:100 */    InetAddress address = socket.getLocalAddress();
/* 101:101 */    socket.close();
/* 102:102 */    return address;
/* 103:    */  }
/* 104:    */  
/* 105:    */  public InetAddress getLocalHostFromInterfaces() throws IOException {
/* 106:106 */    InetAddress possibleAddress = null;
/* 107:    */    
/* 109:    */    try
/* 110:    */    {
/* 111:111 */      Enumeration localIfaces = NetworkInterface.getNetworkInterfaces();
/* 112:112 */      Enumeration addresses; for (; localIfaces.hasMoreElements(); 
/* 113:    */          
/* 118:118 */          addresses.hasMoreElements())
/* 119:    */      {
/* 120:113 */        NetworkInterface iFace = 
/* 121:114 */          (NetworkInterface)localIfaces.nextElement();
/* 122:115 */        addresses = iFace.getInetAddresses();
/* 123:116 */        logger.debug("Interface name: " + iFace.getName());
/* 124:    */        
/* 125:118 */        continue;
/* 126:119 */        InetAddress address = (InetAddress)addresses.nextElement();
/* 127:120 */        logger.debug("Address: " + address);
/* 128:121 */        if (!(address instanceof Inet4Address)) {
/* 129:122 */          logger.debug("Skipping non-IPV4 address " + address);
/* 131:    */        }
/* 132:125 */        else if ((address.isAnyLocalAddress()) || (isWindowsAutoConfiguredIPv4Address(address)) || 
/* 133:126 */          (address.toString().substring(0, 3).equals("/0."))) {
/* 134:127 */          logger.debug("Skipping " + address);
/* 135:    */        }
/* 136:    */        else {
/* 137:130 */          if (address.isLinkLocalAddress()) {
/* 138:131 */            logger.debug("Found Linklocal ipv4 address " + address);
/* 139:132 */            return address;
/* 140:    */          }
/* 141:134 */          if ((possibleAddress == null) && (!address.isLoopbackAddress()) && 
/* 142:135 */            (!address.toString().substring(0, 3).equals("/0."))) {
/* 143:136 */            logger.debug("Setting possible address to " + possibleAddress);
/* 144:137 */            possibleAddress = address;
/* 145:    */          }
/* 146:139 */          if ((iFace.getName().startsWith("cipsec")) && (isReachable(address))) {
/* 147:140 */            logger.debug("Using cipsec " + address);
/* 148:141 */            return address;
/* 149:    */          }
/* 150:143 */          if ((iFace.getName().startsWith("ip.tun")) && (isReachable(address))) {
/* 151:144 */            logger.debug("Using ip.tun " + address);
/* 152:145 */            return address;
/* 153:    */          }
/* 154:    */        }
/* 155:    */      }
/* 156:149 */      if (possibleAddress != null) {
/* 157:150 */        return possibleAddress;
/* 158:    */      }
/* 159:    */      
/* 162:155 */      InetAddress address = InetAddress.getLocalHost();
/* 163:156 */      if (address.toString().substring(0, 3).equals("/0.")) {
/* 164:157 */        String s = "Local address " + address + " is not usable!";
/* 165:158 */        logger.error(s);
/* 166:159 */        throw new IOException(s);
/* 167:    */      }
/* 168:161 */      logger.debug("private local host is " + address);
/* 169:162 */      return address;
/* 170:    */    } catch (Exception e) {
/* 171:164 */      throw new IOException("Failed to get local host! " + e.getMessage());
/* 172:    */    }
/* 173:    */  }
/* 174:    */  
/* 176:    */  public InetSocketAddress getPublicAddressFor(InetSocketAddress stunServer, DatagramSocket socket)
/* 177:    */    throws IOException
/* 178:    */  {
/* 179:172 */    StunClient stunClient = new StunClient(stunServer, socket);
/* 180:173 */    return stunClient.getMappedAddress();
/* 181:    */  }
/* 182:    */  
/* 184:    */  public InetAddress getPublicAddressFor(InetAddress address)
/* 185:    */    throws IOException
/* 186:    */  {
/* 187:180 */    if (this.stunServer != null) {
/* 188:181 */      Socket socket = new Socket();
/* 189:182 */      InetSocketAddress isa = new InetSocketAddress(
/* 190:183 */        this.stunServer, this.stunServerPort);
/* 191:184 */      socket.connect(isa, 10000);
/* 192:185 */      StunClient stunClient = new StunClient(socket);
/* 193:186 */      InetAddress ia = stunClient.getMappedAddress().getAddress();
/* 194:187 */      socket.close();
/* 195:188 */      return ia;
/* 196:    */    }
/* 197:190 */    return address;
/* 198:    */  }
/* 199:    */  
/* 200:    */  public InetAddress getPrivateLocalHost() throws IOException {
/* 201:194 */    if (privateLocalHost == null) {
/* 202:195 */      throw new IOException("Unable to determine localHost!");
/* 203:    */    }
/* 204:197 */    return privateLocalHost;
/* 205:    */  }
/* 206:    */  
/* 207:    */  public InetAddress getPublicLocalHost() throws IOException {
/* 208:201 */    return getPublicAddressFor(privateLocalHost);
/* 209:    */  }
/* 210:    */  
/* 211:    */  private boolean isReachable(InetAddress address) {
/* 212:    */    try {
/* 213:206 */      if (!address.isReachable(timeout))
/* 214:207 */        return false;
/* 215:    */    } catch (IOException e) {
/* 216:209 */      logger.info("can't reach " + address + " " + e.getMessage());
/* 217:210 */      return false;
/* 218:    */    }
/* 219:212 */    return true;
/* 220:    */  }
/* 221:    */  
/* 222:    */  public boolean isLinkLocalIPv4Address(InetAddress addr) {
/* 223:216 */    byte[] address = addr.getAddress();
/* 224:217 */    if ((address[0] & 0xFF) == 10) {
/* 225:218 */      return true;
/* 226:    */    }
/* 227:220 */    if (((address[0] & 0xFF) == 172) && ((address[1] & 0xFF) >= 16) && (address[1] <= 31)) {
/* 228:221 */      return true;
/* 229:    */    }
/* 230:223 */    if (((address[0] & 0xFF) == 192) && ((address[1] & 0xFF) == 168)) {
/* 231:224 */      return true;
/* 232:    */    }
/* 233:226 */    return false;
/* 234:    */  }
/* 235:    */  
/* 236:    */  public boolean isWindowsAutoConfiguredIPv4Address(InetAddress addr) {
/* 237:230 */    return ((addr.getAddress()[0] & 0xFF) == 169) && ((addr.getAddress()[1] & 0xFF) == 254);
/* 238:    */  }
/* 239:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.stun.NetworkAddressManager
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */