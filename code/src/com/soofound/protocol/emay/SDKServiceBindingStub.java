/*   1:    */package com.soofound.protocol.emay;
/*   2:    */
/*   3:    */import java.rmi.RemoteException;
/*   4:    */
/*   5:    */public class SDKServiceBindingStub extends org.apache.axis.client.Stub implements SDKClient
/*   6:    */{
/*   7:  7 */  private java.util.Vector cachedSerClasses = new java.util.Vector();
/*   8:  8 */  private java.util.Vector cachedSerQNames = new java.util.Vector();
/*   9:  9 */  private java.util.Vector cachedSerFactories = new java.util.Vector();
/*  10: 10 */  private java.util.Vector cachedDeserFactories = new java.util.Vector();
/*  11:    */  
/*  15: 15 */  static org.apache.axis.description.OperationDesc[] _operations = new org.apache.axis.description.OperationDesc[14];
/*  16: 16 */  static { _initOperationDesc1();
/*  17: 17 */    _initOperationDesc2();
/*  18:    */  }
/*  19:    */  
/*  21:    */  private static void _initOperationDesc1()
/*  22:    */  {
/*  23: 23 */    org.apache.axis.description.OperationDesc oper = new org.apache.axis.description.OperationDesc();
/*  24: 24 */    oper.setName("getVersion");
/*  25: 25 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
/*  26: 26 */    oper.setReturnClass(String.class);
/*  27: 27 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/*  28: 28 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/*  29: 29 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/*  30: 30 */    _operations[0] = oper;
/*  31:    */    
/*  32: 32 */    oper = new org.apache.axis.description.OperationDesc();
/*  33: 33 */    oper.setName("getReport");
/*  34: 34 */    org.apache.axis.description.ParameterDesc param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  35: 35 */    param.setOmittable(true);
/*  36: 36 */    oper.addParameter(param);
/*  37: 37 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  38: 38 */    param.setOmittable(true);
/*  39: 39 */    oper.addParameter(param);
/*  40: 40 */    oper.setReturnType(new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "statusReport"));
/*  41: 41 */    oper.setReturnClass([Lcom.soofound.protocol.emay.StatusReport.class);
/*  42: 42 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/*  43: 43 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/*  44: 44 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/*  45: 45 */    _operations[1] = oper;
/*  46:    */    
/*  47: 47 */    oper = new org.apache.axis.description.OperationDesc();
/*  48: 48 */    oper.setName("cancelMOForward");
/*  49: 49 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  50: 50 */    param.setOmittable(true);
/*  51: 51 */    oper.addParameter(param);
/*  52: 52 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  53: 53 */    param.setOmittable(true);
/*  54: 54 */    oper.addParameter(param);
/*  55: 55 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/*  56: 56 */    oper.setReturnClass(Integer.TYPE);
/*  57: 57 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/*  58: 58 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/*  59: 59 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/*  60: 60 */    _operations[2] = oper;
/*  61:    */    
/*  62: 62 */    oper = new org.apache.axis.description.OperationDesc();
/*  63: 63 */    oper.setName("chargeUp");
/*  64: 64 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  65: 65 */    param.setOmittable(true);
/*  66: 66 */    oper.addParameter(param);
/*  67: 67 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  68: 68 */    param.setOmittable(true);
/*  69: 69 */    oper.addParameter(param);
/*  70: 70 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg2"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  71: 71 */    param.setOmittable(true);
/*  72: 72 */    oper.addParameter(param);
/*  73: 73 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg3"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  74: 74 */    param.setOmittable(true);
/*  75: 75 */    oper.addParameter(param);
/*  76: 76 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/*  77: 77 */    oper.setReturnClass(Integer.TYPE);
/*  78: 78 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/*  79: 79 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/*  80: 80 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/*  81: 81 */    _operations[3] = oper;
/*  82:    */    
/*  83: 83 */    oper = new org.apache.axis.description.OperationDesc();
/*  84: 84 */    oper.setName("getBalance");
/*  85: 85 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  86: 86 */    param.setOmittable(true);
/*  87: 87 */    oper.addParameter(param);
/*  88: 88 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/*  89: 89 */    param.setOmittable(true);
/*  90: 90 */    oper.addParameter(param);
/*  91: 91 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
/*  92: 92 */    oper.setReturnClass(Double.TYPE);
/*  93: 93 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/*  94: 94 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/*  95: 95 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/*  96: 96 */    _operations[4] = oper;
/*  97:    */    
/*  98: 98 */    oper = new org.apache.axis.description.OperationDesc();
/*  99: 99 */    oper.setName("getEachFee");
/* 100:100 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 101:101 */    param.setOmittable(true);
/* 102:102 */    oper.addParameter(param);
/* 103:103 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 104:104 */    param.setOmittable(true);
/* 105:105 */    oper.addParameter(param);
/* 106:106 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
/* 107:107 */    oper.setReturnClass(Double.TYPE);
/* 108:108 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 109:109 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 110:110 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 111:111 */    _operations[5] = oper;
/* 112:    */    
/* 113:113 */    oper = new org.apache.axis.description.OperationDesc();
/* 114:114 */    oper.setName("getMO");
/* 115:115 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 116:116 */    param.setOmittable(true);
/* 117:117 */    oper.addParameter(param);
/* 118:118 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 119:119 */    param.setOmittable(true);
/* 120:120 */    oper.addParameter(param);
/* 121:121 */    oper.setReturnType(new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "mo"));
/* 122:122 */    oper.setReturnClass([Lcom.soofound.protocol.emay.Mo.class);
/* 123:123 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 124:124 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 125:125 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 126:126 */    _operations[6] = oper;
/* 127:    */    
/* 128:128 */    oper = new org.apache.axis.description.OperationDesc();
/* 129:129 */    oper.setName("logout");
/* 130:130 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 131:131 */    param.setOmittable(true);
/* 132:132 */    oper.addParameter(param);
/* 133:133 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 134:134 */    param.setOmittable(true);
/* 135:135 */    oper.addParameter(param);
/* 136:136 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 137:137 */    oper.setReturnClass(Integer.TYPE);
/* 138:138 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 139:139 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 140:140 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 141:141 */    _operations[7] = oper;
/* 142:    */    
/* 143:143 */    oper = new org.apache.axis.description.OperationDesc();
/* 144:144 */    oper.setName("registDetailInfo");
/* 145:145 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 146:146 */    param.setOmittable(true);
/* 147:147 */    oper.addParameter(param);
/* 148:148 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 149:149 */    param.setOmittable(true);
/* 150:150 */    oper.addParameter(param);
/* 151:151 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg2"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 152:152 */    param.setOmittable(true);
/* 153:153 */    oper.addParameter(param);
/* 154:154 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg3"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 155:155 */    param.setOmittable(true);
/* 156:156 */    oper.addParameter(param);
/* 157:157 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg4"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 158:158 */    param.setOmittable(true);
/* 159:159 */    oper.addParameter(param);
/* 160:160 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg5"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 161:161 */    param.setOmittable(true);
/* 162:162 */    oper.addParameter(param);
/* 163:163 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg6"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 164:164 */    param.setOmittable(true);
/* 165:165 */    oper.addParameter(param);
/* 166:166 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg7"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 167:167 */    param.setOmittable(true);
/* 168:168 */    oper.addParameter(param);
/* 169:169 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg8"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 170:170 */    param.setOmittable(true);
/* 171:171 */    oper.addParameter(param);
/* 172:172 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg9"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 173:173 */    param.setOmittable(true);
/* 174:174 */    oper.addParameter(param);
/* 175:175 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 176:176 */    oper.setReturnClass(Integer.TYPE);
/* 177:177 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 178:178 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 179:179 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 180:180 */    _operations[8] = oper;
/* 181:    */    
/* 182:182 */    oper = new org.apache.axis.description.OperationDesc();
/* 183:183 */    oper.setName("registEx");
/* 184:184 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 185:185 */    param.setOmittable(true);
/* 186:186 */    oper.addParameter(param);
/* 187:187 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 188:188 */    param.setOmittable(true);
/* 189:189 */    oper.addParameter(param);
/* 190:190 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg2"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 191:191 */    param.setOmittable(true);
/* 192:192 */    oper.addParameter(param);
/* 193:193 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 194:194 */    oper.setReturnClass(Integer.TYPE);
/* 195:195 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 196:196 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 197:197 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 198:198 */    _operations[9] = oper;
/* 199:    */  }
/* 200:    */  
/* 203:    */  private static void _initOperationDesc2()
/* 204:    */  {
/* 205:205 */    org.apache.axis.description.OperationDesc oper = new org.apache.axis.description.OperationDesc();
/* 206:206 */    oper.setName("sendSMS");
/* 207:207 */    org.apache.axis.description.ParameterDesc param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 208:208 */    param.setOmittable(true);
/* 209:209 */    oper.addParameter(param);
/* 210:210 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 211:211 */    param.setOmittable(true);
/* 212:212 */    oper.addParameter(param);
/* 213:213 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg2"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 214:214 */    param.setOmittable(true);
/* 215:215 */    oper.addParameter(param);
/* 216:216 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg3"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), [Ljava.lang.String.class, false, false);
/* 217:217 */    param.setOmittable(true);
/* 218:218 */    param.setNillable(true);
/* 219:219 */    oper.addParameter(param);
/* 220:220 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg4"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 221:221 */    param.setOmittable(true);
/* 222:222 */    oper.addParameter(param);
/* 223:223 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg5"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 224:224 */    param.setOmittable(true);
/* 225:225 */    oper.addParameter(param);
/* 226:226 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg6"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 227:227 */    param.setOmittable(true);
/* 228:228 */    oper.addParameter(param);
/* 229:229 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg7"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), Integer.TYPE, false, false);
/* 230:230 */    oper.addParameter(param);
/* 231:231 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg8"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), Long.TYPE, false, false);
/* 232:232 */    oper.addParameter(param);
/* 233:233 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 234:234 */    oper.setReturnClass(Integer.TYPE);
/* 235:235 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 236:236 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 237:237 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 238:238 */    _operations[10] = oper;
/* 239:    */    
/* 240:240 */    oper = new org.apache.axis.description.OperationDesc();
/* 241:241 */    oper.setName("serialPwdUpd");
/* 242:242 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 243:243 */    param.setOmittable(true);
/* 244:244 */    oper.addParameter(param);
/* 245:245 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 246:246 */    param.setOmittable(true);
/* 247:247 */    oper.addParameter(param);
/* 248:248 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg2"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 249:249 */    param.setOmittable(true);
/* 250:250 */    oper.addParameter(param);
/* 251:251 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg3"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 252:252 */    param.setOmittable(true);
/* 253:253 */    oper.addParameter(param);
/* 254:254 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 255:255 */    oper.setReturnClass(Integer.TYPE);
/* 256:256 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 257:257 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 258:258 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 259:259 */    _operations[11] = oper;
/* 260:    */    
/* 261:261 */    oper = new org.apache.axis.description.OperationDesc();
/* 262:262 */    oper.setName("setMOForward");
/* 263:263 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 264:264 */    param.setOmittable(true);
/* 265:265 */    oper.addParameter(param);
/* 266:266 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 267:267 */    param.setOmittable(true);
/* 268:268 */    oper.addParameter(param);
/* 269:269 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg2"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 270:270 */    param.setOmittable(true);
/* 271:271 */    oper.addParameter(param);
/* 272:272 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 273:273 */    oper.setReturnClass(Integer.TYPE);
/* 274:274 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 275:275 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 276:276 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 277:277 */    _operations[12] = oper;
/* 278:    */    
/* 279:279 */    oper = new org.apache.axis.description.OperationDesc();
/* 280:280 */    oper.setName("setMOForwardEx");
/* 281:281 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 282:282 */    param.setOmittable(true);
/* 283:283 */    oper.addParameter(param);
/* 284:284 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg1"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), String.class, false, false);
/* 285:285 */    param.setOmittable(true);
/* 286:286 */    oper.addParameter(param);
/* 287:287 */    param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg2"), (byte)1, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), [Ljava.lang.String.class, false, false);
/* 288:288 */    param.setOmittable(true);
/* 289:289 */    param.setNillable(true);
/* 290:290 */    oper.addParameter(param);
/* 291:291 */    oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
/* 292:292 */    oper.setReturnClass(Integer.TYPE);
/* 293:293 */    oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
/* 294:294 */    oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
/* 295:295 */    oper.setUse(org.apache.axis.constants.Use.LITERAL);
/* 296:296 */    _operations[13] = oper;
/* 297:    */  }
/* 298:    */  
/* 299:    */  public SDKServiceBindingStub() throws org.apache.axis.AxisFault
/* 300:    */  {
/* 301:301 */    this(null);
/* 302:    */  }
/* 303:    */  
/* 304:    */  public SDKServiceBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
/* 305:305 */    this(service);
/* 306:306 */    this.cachedEndpoint = endpointURL;
/* 307:    */  }
/* 308:    */  
/* 309:    */  public SDKServiceBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
/* 310:310 */    if (service == null) {
/* 311:311 */      this.service = new org.apache.axis.client.Service();
/* 312:    */    } else {
/* 313:313 */      this.service = service;
/* 314:    */    }
/* 315:315 */    ((org.apache.axis.client.Service)this.service).setTypeMappingVersion("1.2");
/* 316:    */    
/* 319:319 */    Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
/* 320:320 */    Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
/* 321:321 */    Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
/* 322:322 */    Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
/* 323:323 */    Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
/* 324:324 */    Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
/* 325:325 */    Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
/* 326:326 */    Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
/* 327:327 */    Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
/* 328:328 */    Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
/* 329:329 */    javax.xml.namespace.QName qName = new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "mo");
/* 330:330 */    this.cachedSerQNames.add(qName);
/* 331:331 */    Class cls = Mo.class;
/* 332:332 */    this.cachedSerClasses.add(cls);
/* 333:333 */    this.cachedSerFactories.add(beansf);
/* 334:334 */    this.cachedDeserFactories.add(beandf);
/* 335:    */    
/* 336:336 */    qName = new javax.xml.namespace.QName("http://sdkhttp.eucp.b2m.cn/", "statusReport");
/* 337:337 */    this.cachedSerQNames.add(qName);
/* 338:338 */    cls = StatusReport.class;
/* 339:339 */    this.cachedSerClasses.add(cls);
/* 340:340 */    this.cachedSerFactories.add(beansf);
/* 341:341 */    this.cachedDeserFactories.add(beandf);
/* 342:    */  }
/* 343:    */  
/* 344:    */  protected org.apache.axis.client.Call createCall() throws RemoteException {
/* 345:    */    try {
/* 346:346 */      org.apache.axis.client.Call _call = super._createCall();
/* 347:347 */      if (this.maintainSessionSet) {
/* 348:348 */        _call.setMaintainSession(this.maintainSession);
/* 349:    */      }
/* 350:350 */      if (this.cachedUsername != null) {
/* 351:351 */        _call.setUsername(this.cachedUsername);
/* 352:    */      }
/* 353:353 */      if (this.cachedPassword != null) {
/* 354:354 */        _call.setPassword(this.cachedPassword);
/* 355:    */      }
/* 356:356 */      if (this.cachedEndpoint != null) {
/* 357:357 */        _call.setTargetEndpointAddress(this.cachedEndpoint);
/* 358:    */      }
/* 359:359 */      if (this.cachedTimeout != null) {
/* 360:360 */        _call.setTimeout(this.cachedTimeout);
/* 361:    */      }
/* 362:362 */      if (this.cachedPortName != null) {
/* 363:363 */        _call.setPortName(this.cachedPortName);
/* 364:    */      }
/* 365:365 */      java.util.Enumeration keys = this.cachedProperties.keys();
/* 366:366 */      while (keys.hasMoreElements()) {
/* 367:367 */        String key = (String)keys.nextElement();
/* 368:368 */        _call.setProperty(key, this.cachedProperties.get(key));
/* 369:    */      }
/* 370:    */      
/* 375:375 */      synchronized (this) {
/* 376:376 */        if (firstCall())
/* 377:    */        {
/* 378:378 */          _call.setEncodingStyle(null);
/* 379:379 */          for (int i = 0; i < this.cachedSerFactories.size(); i++) {
/* 380:380 */            Class cls = (Class)this.cachedSerClasses.get(i);
/* 381:381 */            javax.xml.namespace.QName qName = 
/* 382:382 */              (javax.xml.namespace.QName)this.cachedSerQNames.get(i);
/* 383:383 */            Object x = this.cachedSerFactories.get(i);
/* 384:384 */            if ((x instanceof Class)) {
/* 385:385 */              Class sf = 
/* 386:386 */                (Class)this.cachedSerFactories.get(i);
/* 387:387 */              Class df = 
/* 388:388 */                (Class)this.cachedDeserFactories.get(i);
/* 389:389 */              _call.registerTypeMapping(cls, qName, sf, df, false);
/* 390:    */            }
/* 391:391 */            else if ((x instanceof javax.xml.rpc.encoding.SerializerFactory)) {
/* 392:392 */              org.apache.axis.encoding.SerializerFactory sf = 
/* 393:393 */                (org.apache.axis.encoding.SerializerFactory)this.cachedSerFactories.get(i);
/* 394:394 */              org.apache.axis.encoding.DeserializerFactory df = 
/* 395:395 */                (org.apache.axis.encoding.DeserializerFactory)this.cachedDeserFactories.get(i);
/* 396:396 */              _call.registerTypeMapping(cls, qName, sf, df, false);
/* 397:    */            }
/* 398:    */          }
/* 399:    */        }
/* 400:    */      }
/* 401:401 */      return _call;
/* 402:    */    }
/* 403:    */    catch (Throwable _t) {
/* 404:404 */      throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
/* 405:    */    }
/* 406:    */  }
/* 407:    */  
/* 408:    */  /* Error */
/* 409:    */  public String getVersion()
/* 410:    */    throws RemoteException
/* 411:    */  {
/* 412:    */    // Byte code:
/* 413:    */    //   0: aload_0
/* 414:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 415:    */    //   4: ifnonnull +11 -> 15
/* 416:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 417:    */    //   10: dup
/* 418:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 419:    */    //   14: athrow
/* 420:    */    //   15: aload_0
/* 421:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 422:    */    //   19: astore_1
/* 423:    */    //   20: aload_1
/* 424:    */    //   21: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 425:    */    //   24: iconst_0
/* 426:    */    //   25: aaload
/* 427:    */    //   26: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 428:    */    //   29: aload_1
/* 429:    */    //   30: iconst_1
/* 430:    */    //   31: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 431:    */    //   34: aload_1
/* 432:    */    //   35: ldc 57
/* 433:    */    //   37: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 434:    */    //   40: aload_1
/* 435:    */    //   41: aconst_null
/* 436:    */    //   42: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 437:    */    //   45: aload_1
/* 438:    */    //   46: ldc_w 407
/* 439:    */    //   49: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 440:    */    //   52: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 441:    */    //   55: aload_1
/* 442:    */    //   56: ldc_w 415
/* 443:    */    //   59: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 444:    */    //   62: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 445:    */    //   65: aload_1
/* 446:    */    //   66: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 447:    */    //   69: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 448:    */    //   72: aload_1
/* 449:    */    //   73: new 38	javax/xml/namespace/QName
/* 450:    */    //   76: dup
/* 451:    */    //   77: ldc 103
/* 452:    */    //   79: ldc 32
/* 453:    */    //   81: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 454:    */    //   84: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 455:    */    //   87: aload_0
/* 456:    */    //   88: aload_1
/* 457:    */    //   89: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 458:    */    //   92: aload_0
/* 459:    */    //   93: aload_1
/* 460:    */    //   94: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 461:    */    //   97: aload_1
/* 462:    */    //   98: iconst_0
/* 463:    */    //   99: anewarray 389	java/lang/Object
/* 464:    */    //   102: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 465:    */    //   105: astore_2
/* 466:    */    //   106: aload_2
/* 467:    */    //   107: instanceof 264
/* 468:    */    //   110: ifeq +8 -> 118
/* 469:    */    //   113: aload_2
/* 470:    */    //   114: checkcast 264	java/rmi/RemoteException
/* 471:    */    //   117: athrow
/* 472:    */    //   118: aload_0
/* 473:    */    //   119: aload_1
/* 474:    */    //   120: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 475:    */    //   123: aload_2
/* 476:    */    //   124: checkcast 51	java/lang/String
/* 477:    */    //   127: areturn
/* 478:    */    //   128: astore_3
/* 479:    */    //   129: aload_2
/* 480:    */    //   130: ldc 51
/* 481:    */    //   132: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 482:    */    //   135: checkcast 51	java/lang/String
/* 483:    */    //   138: areturn
/* 484:    */    //   139: astore_2
/* 485:    */    //   140: aload_2
/* 486:    */    //   141: athrow
/* 487:    */    // Line number table:
/* 488:    */    //   Java source line #409	-> byte code offset #0
/* 489:    */    //   Java source line #410	-> byte code offset #7
/* 490:    */    //   Java source line #412	-> byte code offset #15
/* 491:    */    //   Java source line #413	-> byte code offset #20
/* 492:    */    //   Java source line #414	-> byte code offset #29
/* 493:    */    //   Java source line #415	-> byte code offset #34
/* 494:    */    //   Java source line #416	-> byte code offset #40
/* 495:    */    //   Java source line #417	-> byte code offset #45
/* 496:    */    //   Java source line #418	-> byte code offset #55
/* 497:    */    //   Java source line #419	-> byte code offset #65
/* 498:    */    //   Java source line #420	-> byte code offset #72
/* 499:    */    //   Java source line #422	-> byte code offset #87
/* 500:    */    //   Java source line #423	-> byte code offset #92
/* 501:    */    //   Java source line #424	-> byte code offset #97
/* 502:    */    //   Java source line #426	-> byte code offset #106
/* 503:    */    //   Java source line #427	-> byte code offset #113
/* 504:    */    //   Java source line #430	-> byte code offset #118
/* 505:    */    //   Java source line #432	-> byte code offset #123
/* 506:    */    //   Java source line #433	-> byte code offset #128
/* 507:    */    //   Java source line #434	-> byte code offset #129
/* 508:    */    //   Java source line #437	-> byte code offset #139
/* 509:    */    //   Java source line #438	-> byte code offset #140
/* 510:    */    // Local variable table:
/* 511:    */    //   start	length	slot	name	signature
/* 512:    */    //   0	142	0	this	SDKServiceBindingStub
/* 513:    */    //   19	101	1	_call	org.apache.axis.client.Call
/* 514:    */    //   105	25	2	_resp	Object
/* 515:    */    //   139	2	2	axisFaultException	org.apache.axis.AxisFault
/* 516:    */    //   128	2	3	_exception	java.lang.Exception
/* 517:    */    // Exception table:
/* 518:    */    //   from	to	target	type
/* 519:    */    //   123	127	128	java/lang/Exception
/* 520:    */    //   97	127	139	org/apache/axis/AxisFault
/* 521:    */    //   128	138	139	org/apache/axis/AxisFault
/* 522:    */  }
/* 523:    */  
/* 524:    */  /* Error */
/* 525:    */  public StatusReport[] getReport(String arg0, String arg1)
/* 526:    */    throws RemoteException
/* 527:    */  {
/* 528:    */    // Byte code:
/* 529:    */    //   0: aload_0
/* 530:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 531:    */    //   4: ifnonnull +11 -> 15
/* 532:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 533:    */    //   10: dup
/* 534:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 535:    */    //   14: athrow
/* 536:    */    //   15: aload_0
/* 537:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 538:    */    //   19: astore_3
/* 539:    */    //   20: aload_3
/* 540:    */    //   21: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 541:    */    //   24: iconst_1
/* 542:    */    //   25: aaload
/* 543:    */    //   26: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 544:    */    //   29: aload_3
/* 545:    */    //   30: iconst_1
/* 546:    */    //   31: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 547:    */    //   34: aload_3
/* 548:    */    //   35: ldc 57
/* 549:    */    //   37: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 550:    */    //   40: aload_3
/* 551:    */    //   41: aconst_null
/* 552:    */    //   42: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 553:    */    //   45: aload_3
/* 554:    */    //   46: ldc_w 407
/* 555:    */    //   49: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 556:    */    //   52: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 557:    */    //   55: aload_3
/* 558:    */    //   56: ldc_w 415
/* 559:    */    //   59: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 560:    */    //   62: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 561:    */    //   65: aload_3
/* 562:    */    //   66: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 563:    */    //   69: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 564:    */    //   72: aload_3
/* 565:    */    //   73: new 38	javax/xml/namespace/QName
/* 566:    */    //   76: dup
/* 567:    */    //   77: ldc 103
/* 568:    */    //   79: ldc 84
/* 569:    */    //   81: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 570:    */    //   84: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 571:    */    //   87: aload_0
/* 572:    */    //   88: aload_3
/* 573:    */    //   89: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 574:    */    //   92: aload_0
/* 575:    */    //   93: aload_3
/* 576:    */    //   94: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 577:    */    //   97: aload_3
/* 578:    */    //   98: iconst_2
/* 579:    */    //   99: anewarray 389	java/lang/Object
/* 580:    */    //   102: dup
/* 581:    */    //   103: iconst_0
/* 582:    */    //   104: aload_1
/* 583:    */    //   105: aastore
/* 584:    */    //   106: dup
/* 585:    */    //   107: iconst_1
/* 586:    */    //   108: aload_2
/* 587:    */    //   109: aastore
/* 588:    */    //   110: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 589:    */    //   113: astore 4
/* 590:    */    //   115: aload 4
/* 591:    */    //   117: instanceof 264
/* 592:    */    //   120: ifeq +9 -> 129
/* 593:    */    //   123: aload 4
/* 594:    */    //   125: checkcast 264	java/rmi/RemoteException
/* 595:    */    //   128: athrow
/* 596:    */    //   129: aload_0
/* 597:    */    //   130: aload_3
/* 598:    */    //   131: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 599:    */    //   134: aload 4
/* 600:    */    //   136: checkcast 107	[Lcom/soofound/protocol/emay/StatusReport;
/* 601:    */    //   139: areturn
/* 602:    */    //   140: astore 5
/* 603:    */    //   142: aload 4
/* 604:    */    //   144: ldc 107
/* 605:    */    //   146: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 606:    */    //   149: checkcast 107	[Lcom/soofound/protocol/emay/StatusReport;
/* 607:    */    //   152: areturn
/* 608:    */    //   153: astore 4
/* 609:    */    //   155: aload 4
/* 610:    */    //   157: athrow
/* 611:    */    // Line number table:
/* 612:    */    //   Java source line #443	-> byte code offset #0
/* 613:    */    //   Java source line #444	-> byte code offset #7
/* 614:    */    //   Java source line #446	-> byte code offset #15
/* 615:    */    //   Java source line #447	-> byte code offset #20
/* 616:    */    //   Java source line #448	-> byte code offset #29
/* 617:    */    //   Java source line #449	-> byte code offset #34
/* 618:    */    //   Java source line #450	-> byte code offset #40
/* 619:    */    //   Java source line #451	-> byte code offset #45
/* 620:    */    //   Java source line #452	-> byte code offset #55
/* 621:    */    //   Java source line #453	-> byte code offset #65
/* 622:    */    //   Java source line #454	-> byte code offset #72
/* 623:    */    //   Java source line #456	-> byte code offset #87
/* 624:    */    //   Java source line #457	-> byte code offset #92
/* 625:    */    //   Java source line #458	-> byte code offset #97
/* 626:    */    //   Java source line #460	-> byte code offset #115
/* 627:    */    //   Java source line #461	-> byte code offset #123
/* 628:    */    //   Java source line #464	-> byte code offset #129
/* 629:    */    //   Java source line #466	-> byte code offset #134
/* 630:    */    //   Java source line #467	-> byte code offset #140
/* 631:    */    //   Java source line #468	-> byte code offset #142
/* 632:    */    //   Java source line #471	-> byte code offset #153
/* 633:    */    //   Java source line #472	-> byte code offset #155
/* 634:    */    // Local variable table:
/* 635:    */    //   start	length	slot	name	signature
/* 636:    */    //   0	158	0	this	SDKServiceBindingStub
/* 637:    */    //   0	158	1	arg0	String
/* 638:    */    //   0	158	2	arg1	String
/* 639:    */    //   19	112	3	_call	org.apache.axis.client.Call
/* 640:    */    //   113	30	4	_resp	Object
/* 641:    */    //   153	3	4	axisFaultException	org.apache.axis.AxisFault
/* 642:    */    //   140	3	5	_exception	java.lang.Exception
/* 643:    */    // Exception table:
/* 644:    */    //   from	to	target	type
/* 645:    */    //   134	139	140	java/lang/Exception
/* 646:    */    //   97	139	153	org/apache/axis/AxisFault
/* 647:    */    //   140	152	153	org/apache/axis/AxisFault
/* 648:    */  }
/* 649:    */  
/* 650:    */  /* Error */
/* 651:    */  public int cancelMOForward(String arg0, String arg1)
/* 652:    */    throws RemoteException
/* 653:    */  {
/* 654:    */    // Byte code:
/* 655:    */    //   0: aload_0
/* 656:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 657:    */    //   4: ifnonnull +11 -> 15
/* 658:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 659:    */    //   10: dup
/* 660:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 661:    */    //   14: athrow
/* 662:    */    //   15: aload_0
/* 663:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 664:    */    //   19: astore_3
/* 665:    */    //   20: aload_3
/* 666:    */    //   21: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 667:    */    //   24: iconst_2
/* 668:    */    //   25: aaload
/* 669:    */    //   26: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 670:    */    //   29: aload_3
/* 671:    */    //   30: iconst_1
/* 672:    */    //   31: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 673:    */    //   34: aload_3
/* 674:    */    //   35: ldc 57
/* 675:    */    //   37: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 676:    */    //   40: aload_3
/* 677:    */    //   41: aconst_null
/* 678:    */    //   42: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 679:    */    //   45: aload_3
/* 680:    */    //   46: ldc_w 407
/* 681:    */    //   49: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 682:    */    //   52: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 683:    */    //   55: aload_3
/* 684:    */    //   56: ldc_w 415
/* 685:    */    //   59: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 686:    */    //   62: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 687:    */    //   65: aload_3
/* 688:    */    //   66: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 689:    */    //   69: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 690:    */    //   72: aload_3
/* 691:    */    //   73: new 38	javax/xml/namespace/QName
/* 692:    */    //   76: dup
/* 693:    */    //   77: ldc 103
/* 694:    */    //   79: ldc 109
/* 695:    */    //   81: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 696:    */    //   84: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 697:    */    //   87: aload_0
/* 698:    */    //   88: aload_3
/* 699:    */    //   89: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 700:    */    //   92: aload_0
/* 701:    */    //   93: aload_3
/* 702:    */    //   94: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 703:    */    //   97: aload_3
/* 704:    */    //   98: iconst_2
/* 705:    */    //   99: anewarray 389	java/lang/Object
/* 706:    */    //   102: dup
/* 707:    */    //   103: iconst_0
/* 708:    */    //   104: aload_1
/* 709:    */    //   105: aastore
/* 710:    */    //   106: dup
/* 711:    */    //   107: iconst_1
/* 712:    */    //   108: aload_2
/* 713:    */    //   109: aastore
/* 714:    */    //   110: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 715:    */    //   113: astore 4
/* 716:    */    //   115: aload 4
/* 717:    */    //   117: instanceof 264
/* 718:    */    //   120: ifeq +9 -> 129
/* 719:    */    //   123: aload 4
/* 720:    */    //   125: checkcast 264	java/rmi/RemoteException
/* 721:    */    //   128: athrow
/* 722:    */    //   129: aload_0
/* 723:    */    //   130: aload_3
/* 724:    */    //   131: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 725:    */    //   134: aload 4
/* 726:    */    //   136: checkcast 114	java/lang/Integer
/* 727:    */    //   139: invokevirtual 459	java/lang/Integer:intValue	()I
/* 728:    */    //   142: ireturn
/* 729:    */    //   143: astore 5
/* 730:    */    //   145: aload 4
/* 731:    */    //   147: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 732:    */    //   150: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 733:    */    //   153: checkcast 114	java/lang/Integer
/* 734:    */    //   156: invokevirtual 459	java/lang/Integer:intValue	()I
/* 735:    */    //   159: ireturn
/* 736:    */    //   160: astore 4
/* 737:    */    //   162: aload 4
/* 738:    */    //   164: athrow
/* 739:    */    // Line number table:
/* 740:    */    //   Java source line #477	-> byte code offset #0
/* 741:    */    //   Java source line #478	-> byte code offset #7
/* 742:    */    //   Java source line #480	-> byte code offset #15
/* 743:    */    //   Java source line #481	-> byte code offset #20
/* 744:    */    //   Java source line #482	-> byte code offset #29
/* 745:    */    //   Java source line #483	-> byte code offset #34
/* 746:    */    //   Java source line #484	-> byte code offset #40
/* 747:    */    //   Java source line #485	-> byte code offset #45
/* 748:    */    //   Java source line #486	-> byte code offset #55
/* 749:    */    //   Java source line #487	-> byte code offset #65
/* 750:    */    //   Java source line #488	-> byte code offset #72
/* 751:    */    //   Java source line #490	-> byte code offset #87
/* 752:    */    //   Java source line #491	-> byte code offset #92
/* 753:    */    //   Java source line #492	-> byte code offset #97
/* 754:    */    //   Java source line #494	-> byte code offset #115
/* 755:    */    //   Java source line #495	-> byte code offset #123
/* 756:    */    //   Java source line #498	-> byte code offset #129
/* 757:    */    //   Java source line #500	-> byte code offset #134
/* 758:    */    //   Java source line #501	-> byte code offset #143
/* 759:    */    //   Java source line #502	-> byte code offset #145
/* 760:    */    //   Java source line #505	-> byte code offset #160
/* 761:    */    //   Java source line #506	-> byte code offset #162
/* 762:    */    // Local variable table:
/* 763:    */    //   start	length	slot	name	signature
/* 764:    */    //   0	165	0	this	SDKServiceBindingStub
/* 765:    */    //   0	165	1	arg0	String
/* 766:    */    //   0	165	2	arg1	String
/* 767:    */    //   19	112	3	_call	org.apache.axis.client.Call
/* 768:    */    //   113	33	4	_resp	Object
/* 769:    */    //   160	3	4	axisFaultException	org.apache.axis.AxisFault
/* 770:    */    //   143	3	5	_exception	java.lang.Exception
/* 771:    */    // Exception table:
/* 772:    */    //   from	to	target	type
/* 773:    */    //   134	142	143	java/lang/Exception
/* 774:    */    //   97	142	160	org/apache/axis/AxisFault
/* 775:    */    //   143	159	160	org/apache/axis/AxisFault
/* 776:    */  }
/* 777:    */  
/* 778:    */  /* Error */
/* 779:    */  public int chargeUp(String arg0, String arg1, String arg2, String arg3)
/* 780:    */    throws RemoteException
/* 781:    */  {
/* 782:    */    // Byte code:
/* 783:    */    //   0: aload_0
/* 784:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 785:    */    //   4: ifnonnull +11 -> 15
/* 786:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 787:    */    //   10: dup
/* 788:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 789:    */    //   14: athrow
/* 790:    */    //   15: aload_0
/* 791:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 792:    */    //   19: astore 5
/* 793:    */    //   21: aload 5
/* 794:    */    //   23: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 795:    */    //   26: iconst_3
/* 796:    */    //   27: aaload
/* 797:    */    //   28: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 798:    */    //   31: aload 5
/* 799:    */    //   33: iconst_1
/* 800:    */    //   34: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 801:    */    //   37: aload 5
/* 802:    */    //   39: ldc 57
/* 803:    */    //   41: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 804:    */    //   44: aload 5
/* 805:    */    //   46: aconst_null
/* 806:    */    //   47: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 807:    */    //   50: aload 5
/* 808:    */    //   52: ldc_w 407
/* 809:    */    //   55: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 810:    */    //   58: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 811:    */    //   61: aload 5
/* 812:    */    //   63: ldc_w 415
/* 813:    */    //   66: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 814:    */    //   69: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 815:    */    //   72: aload 5
/* 816:    */    //   74: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 817:    */    //   77: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 818:    */    //   80: aload 5
/* 819:    */    //   82: new 38	javax/xml/namespace/QName
/* 820:    */    //   85: dup
/* 821:    */    //   86: ldc 103
/* 822:    */    //   88: ldc 119
/* 823:    */    //   90: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 824:    */    //   93: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 825:    */    //   96: aload_0
/* 826:    */    //   97: aload 5
/* 827:    */    //   99: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 828:    */    //   102: aload_0
/* 829:    */    //   103: aload 5
/* 830:    */    //   105: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 831:    */    //   108: aload 5
/* 832:    */    //   110: iconst_4
/* 833:    */    //   111: anewarray 389	java/lang/Object
/* 834:    */    //   114: dup
/* 835:    */    //   115: iconst_0
/* 836:    */    //   116: aload_1
/* 837:    */    //   117: aastore
/* 838:    */    //   118: dup
/* 839:    */    //   119: iconst_1
/* 840:    */    //   120: aload_2
/* 841:    */    //   121: aastore
/* 842:    */    //   122: dup
/* 843:    */    //   123: iconst_2
/* 844:    */    //   124: aload_3
/* 845:    */    //   125: aastore
/* 846:    */    //   126: dup
/* 847:    */    //   127: iconst_3
/* 848:    */    //   128: aload 4
/* 849:    */    //   130: aastore
/* 850:    */    //   131: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 851:    */    //   134: astore 6
/* 852:    */    //   136: aload 6
/* 853:    */    //   138: instanceof 264
/* 854:    */    //   141: ifeq +9 -> 150
/* 855:    */    //   144: aload 6
/* 856:    */    //   146: checkcast 264	java/rmi/RemoteException
/* 857:    */    //   149: athrow
/* 858:    */    //   150: aload_0
/* 859:    */    //   151: aload 5
/* 860:    */    //   153: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 861:    */    //   156: aload 6
/* 862:    */    //   158: checkcast 114	java/lang/Integer
/* 863:    */    //   161: invokevirtual 459	java/lang/Integer:intValue	()I
/* 864:    */    //   164: ireturn
/* 865:    */    //   165: astore 7
/* 866:    */    //   167: aload 6
/* 867:    */    //   169: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 868:    */    //   172: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 869:    */    //   175: checkcast 114	java/lang/Integer
/* 870:    */    //   178: invokevirtual 459	java/lang/Integer:intValue	()I
/* 871:    */    //   181: ireturn
/* 872:    */    //   182: astore 6
/* 873:    */    //   184: aload 6
/* 874:    */    //   186: athrow
/* 875:    */    // Line number table:
/* 876:    */    //   Java source line #511	-> byte code offset #0
/* 877:    */    //   Java source line #512	-> byte code offset #7
/* 878:    */    //   Java source line #514	-> byte code offset #15
/* 879:    */    //   Java source line #515	-> byte code offset #21
/* 880:    */    //   Java source line #516	-> byte code offset #31
/* 881:    */    //   Java source line #517	-> byte code offset #37
/* 882:    */    //   Java source line #518	-> byte code offset #44
/* 883:    */    //   Java source line #519	-> byte code offset #50
/* 884:    */    //   Java source line #520	-> byte code offset #61
/* 885:    */    //   Java source line #521	-> byte code offset #72
/* 886:    */    //   Java source line #522	-> byte code offset #80
/* 887:    */    //   Java source line #524	-> byte code offset #96
/* 888:    */    //   Java source line #525	-> byte code offset #102
/* 889:    */    //   Java source line #526	-> byte code offset #108
/* 890:    */    //   Java source line #528	-> byte code offset #136
/* 891:    */    //   Java source line #529	-> byte code offset #144
/* 892:    */    //   Java source line #532	-> byte code offset #150
/* 893:    */    //   Java source line #534	-> byte code offset #156
/* 894:    */    //   Java source line #535	-> byte code offset #165
/* 895:    */    //   Java source line #536	-> byte code offset #167
/* 896:    */    //   Java source line #539	-> byte code offset #182
/* 897:    */    //   Java source line #540	-> byte code offset #184
/* 898:    */    // Local variable table:
/* 899:    */    //   start	length	slot	name	signature
/* 900:    */    //   0	187	0	this	SDKServiceBindingStub
/* 901:    */    //   0	187	1	arg0	String
/* 902:    */    //   0	187	2	arg1	String
/* 903:    */    //   0	187	3	arg2	String
/* 904:    */    //   0	187	4	arg3	String
/* 905:    */    //   19	133	5	_call	org.apache.axis.client.Call
/* 906:    */    //   134	34	6	_resp	Object
/* 907:    */    //   182	3	6	axisFaultException	org.apache.axis.AxisFault
/* 908:    */    //   165	3	7	_exception	java.lang.Exception
/* 909:    */    // Exception table:
/* 910:    */    //   from	to	target	type
/* 911:    */    //   156	164	165	java/lang/Exception
/* 912:    */    //   108	164	182	org/apache/axis/AxisFault
/* 913:    */    //   165	181	182	org/apache/axis/AxisFault
/* 914:    */  }
/* 915:    */  
/* 916:    */  /* Error */
/* 917:    */  public double getBalance(String arg0, String arg1)
/* 918:    */    throws RemoteException
/* 919:    */  {
/* 920:    */    // Byte code:
/* 921:    */    //   0: aload_0
/* 922:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 923:    */    //   4: ifnonnull +11 -> 15
/* 924:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 925:    */    //   10: dup
/* 926:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 927:    */    //   14: athrow
/* 928:    */    //   15: aload_0
/* 929:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 930:    */    //   19: astore_3
/* 931:    */    //   20: aload_3
/* 932:    */    //   21: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 933:    */    //   24: iconst_4
/* 934:    */    //   25: aaload
/* 935:    */    //   26: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 936:    */    //   29: aload_3
/* 937:    */    //   30: iconst_1
/* 938:    */    //   31: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 939:    */    //   34: aload_3
/* 940:    */    //   35: ldc 57
/* 941:    */    //   37: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 942:    */    //   40: aload_3
/* 943:    */    //   41: aconst_null
/* 944:    */    //   42: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 945:    */    //   45: aload_3
/* 946:    */    //   46: ldc_w 407
/* 947:    */    //   49: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 948:    */    //   52: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 949:    */    //   55: aload_3
/* 950:    */    //   56: ldc_w 415
/* 951:    */    //   59: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 952:    */    //   62: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 953:    */    //   65: aload_3
/* 954:    */    //   66: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 955:    */    //   69: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 956:    */    //   72: aload_3
/* 957:    */    //   73: new 38	javax/xml/namespace/QName
/* 958:    */    //   76: dup
/* 959:    */    //   77: ldc 103
/* 960:    */    //   79: ldc 125
/* 961:    */    //   81: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 962:    */    //   84: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 963:    */    //   87: aload_0
/* 964:    */    //   88: aload_3
/* 965:    */    //   89: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 966:    */    //   92: aload_0
/* 967:    */    //   93: aload_3
/* 968:    */    //   94: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 969:    */    //   97: aload_3
/* 970:    */    //   98: iconst_2
/* 971:    */    //   99: anewarray 389	java/lang/Object
/* 972:    */    //   102: dup
/* 973:    */    //   103: iconst_0
/* 974:    */    //   104: aload_1
/* 975:    */    //   105: aastore
/* 976:    */    //   106: dup
/* 977:    */    //   107: iconst_1
/* 978:    */    //   108: aload_2
/* 979:    */    //   109: aastore
/* 980:    */    //   110: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 981:    */    //   113: astore 4
/* 982:    */    //   115: aload 4
/* 983:    */    //   117: instanceof 264
/* 984:    */    //   120: ifeq +9 -> 129
/* 985:    */    //   123: aload 4
/* 986:    */    //   125: checkcast 264	java/rmi/RemoteException
/* 987:    */    //   128: athrow
/* 988:    */    //   129: aload_0
/* 989:    */    //   130: aload_3
/* 990:    */    //   131: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 991:    */    //   134: aload 4
/* 992:    */    //   136: checkcast 130	java/lang/Double
/* 993:    */    //   139: invokevirtual 464	java/lang/Double:doubleValue	()D
/* 994:    */    //   142: dreturn
/* 995:    */    //   143: astore 5
/* 996:    */    //   145: aload 4
/* 997:    */    //   147: getstatic 129	java/lang/Double:TYPE	Ljava/lang/Class;
/* 998:    */    //   150: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 999:    */    //   153: checkcast 130	java/lang/Double
/* 1000:    */    //   156: invokevirtual 464	java/lang/Double:doubleValue	()D
/* 1001:    */    //   159: dreturn
/* 1002:    */    //   160: astore 4
/* 1003:    */    //   162: aload 4
/* 1004:    */    //   164: athrow
/* 1005:    */    // Line number table:
/* 1006:    */    //   Java source line #545	-> byte code offset #0
/* 1007:    */    //   Java source line #546	-> byte code offset #7
/* 1008:    */    //   Java source line #548	-> byte code offset #15
/* 1009:    */    //   Java source line #549	-> byte code offset #20
/* 1010:    */    //   Java source line #550	-> byte code offset #29
/* 1011:    */    //   Java source line #551	-> byte code offset #34
/* 1012:    */    //   Java source line #552	-> byte code offset #40
/* 1013:    */    //   Java source line #553	-> byte code offset #45
/* 1014:    */    //   Java source line #554	-> byte code offset #55
/* 1015:    */    //   Java source line #555	-> byte code offset #65
/* 1016:    */    //   Java source line #556	-> byte code offset #72
/* 1017:    */    //   Java source line #558	-> byte code offset #87
/* 1018:    */    //   Java source line #559	-> byte code offset #92
/* 1019:    */    //   Java source line #560	-> byte code offset #97
/* 1020:    */    //   Java source line #562	-> byte code offset #115
/* 1021:    */    //   Java source line #563	-> byte code offset #123
/* 1022:    */    //   Java source line #566	-> byte code offset #129
/* 1023:    */    //   Java source line #568	-> byte code offset #134
/* 1024:    */    //   Java source line #569	-> byte code offset #143
/* 1025:    */    //   Java source line #570	-> byte code offset #145
/* 1026:    */    //   Java source line #573	-> byte code offset #160
/* 1027:    */    //   Java source line #574	-> byte code offset #162
/* 1028:    */    // Local variable table:
/* 1029:    */    //   start	length	slot	name	signature
/* 1030:    */    //   0	165	0	this	SDKServiceBindingStub
/* 1031:    */    //   0	165	1	arg0	String
/* 1032:    */    //   0	165	2	arg1	String
/* 1033:    */    //   19	112	3	_call	org.apache.axis.client.Call
/* 1034:    */    //   113	33	4	_resp	Object
/* 1035:    */    //   160	3	4	axisFaultException	org.apache.axis.AxisFault
/* 1036:    */    //   143	3	5	_exception	java.lang.Exception
/* 1037:    */    // Exception table:
/* 1038:    */    //   from	to	target	type
/* 1039:    */    //   134	142	143	java/lang/Exception
/* 1040:    */    //   97	142	160	org/apache/axis/AxisFault
/* 1041:    */    //   143	159	160	org/apache/axis/AxisFault
/* 1042:    */  }
/* 1043:    */  
/* 1044:    */  /* Error */
/* 1045:    */  public double getEachFee(String arg0, String arg1)
/* 1046:    */    throws RemoteException
/* 1047:    */  {
/* 1048:    */    // Byte code:
/* 1049:    */    //   0: aload_0
/* 1050:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 1051:    */    //   4: ifnonnull +11 -> 15
/* 1052:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 1053:    */    //   10: dup
/* 1054:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 1055:    */    //   14: athrow
/* 1056:    */    //   15: aload_0
/* 1057:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 1058:    */    //   19: astore_3
/* 1059:    */    //   20: aload_3
/* 1060:    */    //   21: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 1061:    */    //   24: iconst_5
/* 1062:    */    //   25: aaload
/* 1063:    */    //   26: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 1064:    */    //   29: aload_3
/* 1065:    */    //   30: iconst_1
/* 1066:    */    //   31: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 1067:    */    //   34: aload_3
/* 1068:    */    //   35: ldc 57
/* 1069:    */    //   37: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 1070:    */    //   40: aload_3
/* 1071:    */    //   41: aconst_null
/* 1072:    */    //   42: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 1073:    */    //   45: aload_3
/* 1074:    */    //   46: ldc_w 407
/* 1075:    */    //   49: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1076:    */    //   52: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1077:    */    //   55: aload_3
/* 1078:    */    //   56: ldc_w 415
/* 1079:    */    //   59: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1080:    */    //   62: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1081:    */    //   65: aload_3
/* 1082:    */    //   66: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 1083:    */    //   69: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 1084:    */    //   72: aload_3
/* 1085:    */    //   73: new 38	javax/xml/namespace/QName
/* 1086:    */    //   76: dup
/* 1087:    */    //   77: ldc 103
/* 1088:    */    //   79: ldc 132
/* 1089:    */    //   81: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 1090:    */    //   84: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 1091:    */    //   87: aload_0
/* 1092:    */    //   88: aload_3
/* 1093:    */    //   89: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 1094:    */    //   92: aload_0
/* 1095:    */    //   93: aload_3
/* 1096:    */    //   94: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 1097:    */    //   97: aload_3
/* 1098:    */    //   98: iconst_2
/* 1099:    */    //   99: anewarray 389	java/lang/Object
/* 1100:    */    //   102: dup
/* 1101:    */    //   103: iconst_0
/* 1102:    */    //   104: aload_1
/* 1103:    */    //   105: aastore
/* 1104:    */    //   106: dup
/* 1105:    */    //   107: iconst_1
/* 1106:    */    //   108: aload_2
/* 1107:    */    //   109: aastore
/* 1108:    */    //   110: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 1109:    */    //   113: astore 4
/* 1110:    */    //   115: aload 4
/* 1111:    */    //   117: instanceof 264
/* 1112:    */    //   120: ifeq +9 -> 129
/* 1113:    */    //   123: aload 4
/* 1114:    */    //   125: checkcast 264	java/rmi/RemoteException
/* 1115:    */    //   128: athrow
/* 1116:    */    //   129: aload_0
/* 1117:    */    //   130: aload_3
/* 1118:    */    //   131: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 1119:    */    //   134: aload 4
/* 1120:    */    //   136: checkcast 130	java/lang/Double
/* 1121:    */    //   139: invokevirtual 464	java/lang/Double:doubleValue	()D
/* 1122:    */    //   142: dreturn
/* 1123:    */    //   143: astore 5
/* 1124:    */    //   145: aload 4
/* 1125:    */    //   147: getstatic 129	java/lang/Double:TYPE	Ljava/lang/Class;
/* 1126:    */    //   150: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 1127:    */    //   153: checkcast 130	java/lang/Double
/* 1128:    */    //   156: invokevirtual 464	java/lang/Double:doubleValue	()D
/* 1129:    */    //   159: dreturn
/* 1130:    */    //   160: astore 4
/* 1131:    */    //   162: aload 4
/* 1132:    */    //   164: athrow
/* 1133:    */    // Line number table:
/* 1134:    */    //   Java source line #579	-> byte code offset #0
/* 1135:    */    //   Java source line #580	-> byte code offset #7
/* 1136:    */    //   Java source line #582	-> byte code offset #15
/* 1137:    */    //   Java source line #583	-> byte code offset #20
/* 1138:    */    //   Java source line #584	-> byte code offset #29
/* 1139:    */    //   Java source line #585	-> byte code offset #34
/* 1140:    */    //   Java source line #586	-> byte code offset #40
/* 1141:    */    //   Java source line #587	-> byte code offset #45
/* 1142:    */    //   Java source line #588	-> byte code offset #55
/* 1143:    */    //   Java source line #589	-> byte code offset #65
/* 1144:    */    //   Java source line #590	-> byte code offset #72
/* 1145:    */    //   Java source line #592	-> byte code offset #87
/* 1146:    */    //   Java source line #593	-> byte code offset #92
/* 1147:    */    //   Java source line #594	-> byte code offset #97
/* 1148:    */    //   Java source line #596	-> byte code offset #115
/* 1149:    */    //   Java source line #597	-> byte code offset #123
/* 1150:    */    //   Java source line #600	-> byte code offset #129
/* 1151:    */    //   Java source line #602	-> byte code offset #134
/* 1152:    */    //   Java source line #603	-> byte code offset #143
/* 1153:    */    //   Java source line #604	-> byte code offset #145
/* 1154:    */    //   Java source line #607	-> byte code offset #160
/* 1155:    */    //   Java source line #608	-> byte code offset #162
/* 1156:    */    // Local variable table:
/* 1157:    */    //   start	length	slot	name	signature
/* 1158:    */    //   0	165	0	this	SDKServiceBindingStub
/* 1159:    */    //   0	165	1	arg0	String
/* 1160:    */    //   0	165	2	arg1	String
/* 1161:    */    //   19	112	3	_call	org.apache.axis.client.Call
/* 1162:    */    //   113	33	4	_resp	Object
/* 1163:    */    //   160	3	4	axisFaultException	org.apache.axis.AxisFault
/* 1164:    */    //   143	3	5	_exception	java.lang.Exception
/* 1165:    */    // Exception table:
/* 1166:    */    //   from	to	target	type
/* 1167:    */    //   134	142	143	java/lang/Exception
/* 1168:    */    //   97	142	160	org/apache/axis/AxisFault
/* 1169:    */    //   143	159	160	org/apache/axis/AxisFault
/* 1170:    */  }
/* 1171:    */  
/* 1172:    */  /* Error */
/* 1173:    */  public Mo[] getMO(String arg0, String arg1)
/* 1174:    */    throws RemoteException
/* 1175:    */  {
/* 1176:    */    // Byte code:
/* 1177:    */    //   0: aload_0
/* 1178:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 1179:    */    //   4: ifnonnull +11 -> 15
/* 1180:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 1181:    */    //   10: dup
/* 1182:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 1183:    */    //   14: athrow
/* 1184:    */    //   15: aload_0
/* 1185:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 1186:    */    //   19: astore_3
/* 1187:    */    //   20: aload_3
/* 1188:    */    //   21: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 1189:    */    //   24: bipush 6
/* 1190:    */    //   26: aaload
/* 1191:    */    //   27: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 1192:    */    //   30: aload_3
/* 1193:    */    //   31: iconst_1
/* 1194:    */    //   32: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 1195:    */    //   35: aload_3
/* 1196:    */    //   36: ldc 57
/* 1197:    */    //   38: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 1198:    */    //   41: aload_3
/* 1199:    */    //   42: aconst_null
/* 1200:    */    //   43: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 1201:    */    //   46: aload_3
/* 1202:    */    //   47: ldc_w 407
/* 1203:    */    //   50: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1204:    */    //   53: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1205:    */    //   56: aload_3
/* 1206:    */    //   57: ldc_w 415
/* 1207:    */    //   60: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1208:    */    //   63: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1209:    */    //   66: aload_3
/* 1210:    */    //   67: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 1211:    */    //   70: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 1212:    */    //   73: aload_3
/* 1213:    */    //   74: new 38	javax/xml/namespace/QName
/* 1214:    */    //   77: dup
/* 1215:    */    //   78: ldc 103
/* 1216:    */    //   80: ldc 134
/* 1217:    */    //   82: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 1218:    */    //   85: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 1219:    */    //   88: aload_0
/* 1220:    */    //   89: aload_3
/* 1221:    */    //   90: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 1222:    */    //   93: aload_0
/* 1223:    */    //   94: aload_3
/* 1224:    */    //   95: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 1225:    */    //   98: aload_3
/* 1226:    */    //   99: iconst_2
/* 1227:    */    //   100: anewarray 389	java/lang/Object
/* 1228:    */    //   103: dup
/* 1229:    */    //   104: iconst_0
/* 1230:    */    //   105: aload_1
/* 1231:    */    //   106: aastore
/* 1232:    */    //   107: dup
/* 1233:    */    //   108: iconst_1
/* 1234:    */    //   109: aload_2
/* 1235:    */    //   110: aastore
/* 1236:    */    //   111: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 1237:    */    //   114: astore 4
/* 1238:    */    //   116: aload 4
/* 1239:    */    //   118: instanceof 264
/* 1240:    */    //   121: ifeq +9 -> 130
/* 1241:    */    //   124: aload 4
/* 1242:    */    //   126: checkcast 264	java/rmi/RemoteException
/* 1243:    */    //   129: athrow
/* 1244:    */    //   130: aload_0
/* 1245:    */    //   131: aload_3
/* 1246:    */    //   132: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 1247:    */    //   135: aload 4
/* 1248:    */    //   137: checkcast 138	[Lcom/soofound/protocol/emay/Mo;
/* 1249:    */    //   140: areturn
/* 1250:    */    //   141: astore 5
/* 1251:    */    //   143: aload 4
/* 1252:    */    //   145: ldc 138
/* 1253:    */    //   147: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 1254:    */    //   150: checkcast 138	[Lcom/soofound/protocol/emay/Mo;
/* 1255:    */    //   153: areturn
/* 1256:    */    //   154: astore 4
/* 1257:    */    //   156: aload 4
/* 1258:    */    //   158: athrow
/* 1259:    */    // Line number table:
/* 1260:    */    //   Java source line #613	-> byte code offset #0
/* 1261:    */    //   Java source line #614	-> byte code offset #7
/* 1262:    */    //   Java source line #616	-> byte code offset #15
/* 1263:    */    //   Java source line #617	-> byte code offset #20
/* 1264:    */    //   Java source line #618	-> byte code offset #30
/* 1265:    */    //   Java source line #619	-> byte code offset #35
/* 1266:    */    //   Java source line #620	-> byte code offset #41
/* 1267:    */    //   Java source line #621	-> byte code offset #46
/* 1268:    */    //   Java source line #622	-> byte code offset #56
/* 1269:    */    //   Java source line #623	-> byte code offset #66
/* 1270:    */    //   Java source line #624	-> byte code offset #73
/* 1271:    */    //   Java source line #626	-> byte code offset #88
/* 1272:    */    //   Java source line #627	-> byte code offset #93
/* 1273:    */    //   Java source line #628	-> byte code offset #98
/* 1274:    */    //   Java source line #630	-> byte code offset #116
/* 1275:    */    //   Java source line #631	-> byte code offset #124
/* 1276:    */    //   Java source line #634	-> byte code offset #130
/* 1277:    */    //   Java source line #636	-> byte code offset #135
/* 1278:    */    //   Java source line #637	-> byte code offset #141
/* 1279:    */    //   Java source line #638	-> byte code offset #143
/* 1280:    */    //   Java source line #641	-> byte code offset #154
/* 1281:    */    //   Java source line #642	-> byte code offset #156
/* 1282:    */    // Local variable table:
/* 1283:    */    //   start	length	slot	name	signature
/* 1284:    */    //   0	159	0	this	SDKServiceBindingStub
/* 1285:    */    //   0	159	1	arg0	String
/* 1286:    */    //   0	159	2	arg1	String
/* 1287:    */    //   19	113	3	_call	org.apache.axis.client.Call
/* 1288:    */    //   114	30	4	_resp	Object
/* 1289:    */    //   154	3	4	axisFaultException	org.apache.axis.AxisFault
/* 1290:    */    //   141	3	5	_exception	java.lang.Exception
/* 1291:    */    // Exception table:
/* 1292:    */    //   from	to	target	type
/* 1293:    */    //   135	140	141	java/lang/Exception
/* 1294:    */    //   98	140	154	org/apache/axis/AxisFault
/* 1295:    */    //   141	153	154	org/apache/axis/AxisFault
/* 1296:    */  }
/* 1297:    */  
/* 1298:    */  /* Error */
/* 1299:    */  public int logout(String arg0, String arg1)
/* 1300:    */    throws RemoteException
/* 1301:    */  {
/* 1302:    */    // Byte code:
/* 1303:    */    //   0: aload_0
/* 1304:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 1305:    */    //   4: ifnonnull +11 -> 15
/* 1306:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 1307:    */    //   10: dup
/* 1308:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 1309:    */    //   14: athrow
/* 1310:    */    //   15: aload_0
/* 1311:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 1312:    */    //   19: astore_3
/* 1313:    */    //   20: aload_3
/* 1314:    */    //   21: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 1315:    */    //   24: bipush 7
/* 1316:    */    //   26: aaload
/* 1317:    */    //   27: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 1318:    */    //   30: aload_3
/* 1319:    */    //   31: iconst_1
/* 1320:    */    //   32: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 1321:    */    //   35: aload_3
/* 1322:    */    //   36: ldc 57
/* 1323:    */    //   38: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 1324:    */    //   41: aload_3
/* 1325:    */    //   42: aconst_null
/* 1326:    */    //   43: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 1327:    */    //   46: aload_3
/* 1328:    */    //   47: ldc_w 407
/* 1329:    */    //   50: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1330:    */    //   53: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1331:    */    //   56: aload_3
/* 1332:    */    //   57: ldc_w 415
/* 1333:    */    //   60: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1334:    */    //   63: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1335:    */    //   66: aload_3
/* 1336:    */    //   67: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 1337:    */    //   70: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 1338:    */    //   73: aload_3
/* 1339:    */    //   74: new 38	javax/xml/namespace/QName
/* 1340:    */    //   77: dup
/* 1341:    */    //   78: ldc 103
/* 1342:    */    //   80: ldc 140
/* 1343:    */    //   82: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 1344:    */    //   85: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 1345:    */    //   88: aload_0
/* 1346:    */    //   89: aload_3
/* 1347:    */    //   90: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 1348:    */    //   93: aload_0
/* 1349:    */    //   94: aload_3
/* 1350:    */    //   95: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 1351:    */    //   98: aload_3
/* 1352:    */    //   99: iconst_2
/* 1353:    */    //   100: anewarray 389	java/lang/Object
/* 1354:    */    //   103: dup
/* 1355:    */    //   104: iconst_0
/* 1356:    */    //   105: aload_1
/* 1357:    */    //   106: aastore
/* 1358:    */    //   107: dup
/* 1359:    */    //   108: iconst_1
/* 1360:    */    //   109: aload_2
/* 1361:    */    //   110: aastore
/* 1362:    */    //   111: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 1363:    */    //   114: astore 4
/* 1364:    */    //   116: aload 4
/* 1365:    */    //   118: instanceof 264
/* 1366:    */    //   121: ifeq +9 -> 130
/* 1367:    */    //   124: aload 4
/* 1368:    */    //   126: checkcast 264	java/rmi/RemoteException
/* 1369:    */    //   129: athrow
/* 1370:    */    //   130: aload_0
/* 1371:    */    //   131: aload_3
/* 1372:    */    //   132: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 1373:    */    //   135: aload 4
/* 1374:    */    //   137: checkcast 114	java/lang/Integer
/* 1375:    */    //   140: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1376:    */    //   143: ireturn
/* 1377:    */    //   144: astore 5
/* 1378:    */    //   146: aload 4
/* 1379:    */    //   148: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 1380:    */    //   151: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 1381:    */    //   154: checkcast 114	java/lang/Integer
/* 1382:    */    //   157: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1383:    */    //   160: ireturn
/* 1384:    */    //   161: astore 4
/* 1385:    */    //   163: aload 4
/* 1386:    */    //   165: athrow
/* 1387:    */    // Line number table:
/* 1388:    */    //   Java source line #647	-> byte code offset #0
/* 1389:    */    //   Java source line #648	-> byte code offset #7
/* 1390:    */    //   Java source line #650	-> byte code offset #15
/* 1391:    */    //   Java source line #651	-> byte code offset #20
/* 1392:    */    //   Java source line #652	-> byte code offset #30
/* 1393:    */    //   Java source line #653	-> byte code offset #35
/* 1394:    */    //   Java source line #654	-> byte code offset #41
/* 1395:    */    //   Java source line #655	-> byte code offset #46
/* 1396:    */    //   Java source line #656	-> byte code offset #56
/* 1397:    */    //   Java source line #657	-> byte code offset #66
/* 1398:    */    //   Java source line #658	-> byte code offset #73
/* 1399:    */    //   Java source line #660	-> byte code offset #88
/* 1400:    */    //   Java source line #661	-> byte code offset #93
/* 1401:    */    //   Java source line #662	-> byte code offset #98
/* 1402:    */    //   Java source line #664	-> byte code offset #116
/* 1403:    */    //   Java source line #665	-> byte code offset #124
/* 1404:    */    //   Java source line #668	-> byte code offset #130
/* 1405:    */    //   Java source line #670	-> byte code offset #135
/* 1406:    */    //   Java source line #671	-> byte code offset #144
/* 1407:    */    //   Java source line #672	-> byte code offset #146
/* 1408:    */    //   Java source line #675	-> byte code offset #161
/* 1409:    */    //   Java source line #676	-> byte code offset #163
/* 1410:    */    // Local variable table:
/* 1411:    */    //   start	length	slot	name	signature
/* 1412:    */    //   0	166	0	this	SDKServiceBindingStub
/* 1413:    */    //   0	166	1	arg0	String
/* 1414:    */    //   0	166	2	arg1	String
/* 1415:    */    //   19	113	3	_call	org.apache.axis.client.Call
/* 1416:    */    //   114	33	4	_resp	Object
/* 1417:    */    //   161	3	4	axisFaultException	org.apache.axis.AxisFault
/* 1418:    */    //   144	3	5	_exception	java.lang.Exception
/* 1419:    */    // Exception table:
/* 1420:    */    //   from	to	target	type
/* 1421:    */    //   135	143	144	java/lang/Exception
/* 1422:    */    //   98	143	161	org/apache/axis/AxisFault
/* 1423:    */    //   144	160	161	org/apache/axis/AxisFault
/* 1424:    */  }
/* 1425:    */  
/* 1426:    */  /* Error */
/* 1427:    */  public int registDetailInfo(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5, String arg6, String arg7, String arg8, String arg9)
/* 1428:    */    throws RemoteException
/* 1429:    */  {
/* 1430:    */    // Byte code:
/* 1431:    */    //   0: aload_0
/* 1432:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 1433:    */    //   4: ifnonnull +11 -> 15
/* 1434:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 1435:    */    //   10: dup
/* 1436:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 1437:    */    //   14: athrow
/* 1438:    */    //   15: aload_0
/* 1439:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 1440:    */    //   19: astore 11
/* 1441:    */    //   21: aload 11
/* 1442:    */    //   23: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 1443:    */    //   26: bipush 8
/* 1444:    */    //   28: aaload
/* 1445:    */    //   29: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 1446:    */    //   32: aload 11
/* 1447:    */    //   34: iconst_1
/* 1448:    */    //   35: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 1449:    */    //   38: aload 11
/* 1450:    */    //   40: ldc 57
/* 1451:    */    //   42: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 1452:    */    //   45: aload 11
/* 1453:    */    //   47: aconst_null
/* 1454:    */    //   48: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 1455:    */    //   51: aload 11
/* 1456:    */    //   53: ldc_w 407
/* 1457:    */    //   56: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1458:    */    //   59: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1459:    */    //   62: aload 11
/* 1460:    */    //   64: ldc_w 415
/* 1461:    */    //   67: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1462:    */    //   70: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1463:    */    //   73: aload 11
/* 1464:    */    //   75: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 1465:    */    //   78: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 1466:    */    //   81: aload 11
/* 1467:    */    //   83: new 38	javax/xml/namespace/QName
/* 1468:    */    //   86: dup
/* 1469:    */    //   87: ldc 103
/* 1470:    */    //   89: ldc 142
/* 1471:    */    //   91: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 1472:    */    //   94: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 1473:    */    //   97: aload_0
/* 1474:    */    //   98: aload 11
/* 1475:    */    //   100: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 1476:    */    //   103: aload_0
/* 1477:    */    //   104: aload 11
/* 1478:    */    //   106: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 1479:    */    //   109: aload 11
/* 1480:    */    //   111: bipush 10
/* 1481:    */    //   113: anewarray 389	java/lang/Object
/* 1482:    */    //   116: dup
/* 1483:    */    //   117: iconst_0
/* 1484:    */    //   118: aload_1
/* 1485:    */    //   119: aastore
/* 1486:    */    //   120: dup
/* 1487:    */    //   121: iconst_1
/* 1488:    */    //   122: aload_2
/* 1489:    */    //   123: aastore
/* 1490:    */    //   124: dup
/* 1491:    */    //   125: iconst_2
/* 1492:    */    //   126: aload_3
/* 1493:    */    //   127: aastore
/* 1494:    */    //   128: dup
/* 1495:    */    //   129: iconst_3
/* 1496:    */    //   130: aload 4
/* 1497:    */    //   132: aastore
/* 1498:    */    //   133: dup
/* 1499:    */    //   134: iconst_4
/* 1500:    */    //   135: aload 5
/* 1501:    */    //   137: aastore
/* 1502:    */    //   138: dup
/* 1503:    */    //   139: iconst_5
/* 1504:    */    //   140: aload 6
/* 1505:    */    //   142: aastore
/* 1506:    */    //   143: dup
/* 1507:    */    //   144: bipush 6
/* 1508:    */    //   146: aload 7
/* 1509:    */    //   148: aastore
/* 1510:    */    //   149: dup
/* 1511:    */    //   150: bipush 7
/* 1512:    */    //   152: aload 8
/* 1513:    */    //   154: aastore
/* 1514:    */    //   155: dup
/* 1515:    */    //   156: bipush 8
/* 1516:    */    //   158: aload 9
/* 1517:    */    //   160: aastore
/* 1518:    */    //   161: dup
/* 1519:    */    //   162: bipush 9
/* 1520:    */    //   164: aload 10
/* 1521:    */    //   166: aastore
/* 1522:    */    //   167: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 1523:    */    //   170: astore 12
/* 1524:    */    //   172: aload 12
/* 1525:    */    //   174: instanceof 264
/* 1526:    */    //   177: ifeq +9 -> 186
/* 1527:    */    //   180: aload 12
/* 1528:    */    //   182: checkcast 264	java/rmi/RemoteException
/* 1529:    */    //   185: athrow
/* 1530:    */    //   186: aload_0
/* 1531:    */    //   187: aload 11
/* 1532:    */    //   189: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 1533:    */    //   192: aload 12
/* 1534:    */    //   194: checkcast 114	java/lang/Integer
/* 1535:    */    //   197: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1536:    */    //   200: ireturn
/* 1537:    */    //   201: astore 13
/* 1538:    */    //   203: aload 12
/* 1539:    */    //   205: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 1540:    */    //   208: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 1541:    */    //   211: checkcast 114	java/lang/Integer
/* 1542:    */    //   214: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1543:    */    //   217: ireturn
/* 1544:    */    //   218: astore 12
/* 1545:    */    //   220: aload 12
/* 1546:    */    //   222: athrow
/* 1547:    */    // Line number table:
/* 1548:    */    //   Java source line #681	-> byte code offset #0
/* 1549:    */    //   Java source line #682	-> byte code offset #7
/* 1550:    */    //   Java source line #684	-> byte code offset #15
/* 1551:    */    //   Java source line #685	-> byte code offset #21
/* 1552:    */    //   Java source line #686	-> byte code offset #32
/* 1553:    */    //   Java source line #687	-> byte code offset #38
/* 1554:    */    //   Java source line #688	-> byte code offset #45
/* 1555:    */    //   Java source line #689	-> byte code offset #51
/* 1556:    */    //   Java source line #690	-> byte code offset #62
/* 1557:    */    //   Java source line #691	-> byte code offset #73
/* 1558:    */    //   Java source line #692	-> byte code offset #81
/* 1559:    */    //   Java source line #694	-> byte code offset #97
/* 1560:    */    //   Java source line #695	-> byte code offset #103
/* 1561:    */    //   Java source line #696	-> byte code offset #109
/* 1562:    */    //   Java source line #698	-> byte code offset #172
/* 1563:    */    //   Java source line #699	-> byte code offset #180
/* 1564:    */    //   Java source line #702	-> byte code offset #186
/* 1565:    */    //   Java source line #704	-> byte code offset #192
/* 1566:    */    //   Java source line #705	-> byte code offset #201
/* 1567:    */    //   Java source line #706	-> byte code offset #203
/* 1568:    */    //   Java source line #709	-> byte code offset #218
/* 1569:    */    //   Java source line #710	-> byte code offset #220
/* 1570:    */    // Local variable table:
/* 1571:    */    //   start	length	slot	name	signature
/* 1572:    */    //   0	223	0	this	SDKServiceBindingStub
/* 1573:    */    //   0	223	1	arg0	String
/* 1574:    */    //   0	223	2	arg1	String
/* 1575:    */    //   0	223	3	arg2	String
/* 1576:    */    //   0	223	4	arg3	String
/* 1577:    */    //   0	223	5	arg4	String
/* 1578:    */    //   0	223	6	arg5	String
/* 1579:    */    //   0	223	7	arg6	String
/* 1580:    */    //   0	223	8	arg7	String
/* 1581:    */    //   0	223	9	arg8	String
/* 1582:    */    //   0	223	10	arg9	String
/* 1583:    */    //   19	169	11	_call	org.apache.axis.client.Call
/* 1584:    */    //   170	34	12	_resp	Object
/* 1585:    */    //   218	3	12	axisFaultException	org.apache.axis.AxisFault
/* 1586:    */    //   201	3	13	_exception	java.lang.Exception
/* 1587:    */    // Exception table:
/* 1588:    */    //   from	to	target	type
/* 1589:    */    //   192	200	201	java/lang/Exception
/* 1590:    */    //   109	200	218	org/apache/axis/AxisFault
/* 1591:    */    //   201	217	218	org/apache/axis/AxisFault
/* 1592:    */  }
/* 1593:    */  
/* 1594:    */  /* Error */
/* 1595:    */  public int registEx(String arg0, String arg1, String arg2)
/* 1596:    */    throws RemoteException
/* 1597:    */  {
/* 1598:    */    // Byte code:
/* 1599:    */    //   0: aload_0
/* 1600:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 1601:    */    //   4: ifnonnull +11 -> 15
/* 1602:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 1603:    */    //   10: dup
/* 1604:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 1605:    */    //   14: athrow
/* 1606:    */    //   15: aload_0
/* 1607:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 1608:    */    //   19: astore 4
/* 1609:    */    //   21: aload 4
/* 1610:    */    //   23: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 1611:    */    //   26: bipush 9
/* 1612:    */    //   28: aaload
/* 1613:    */    //   29: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 1614:    */    //   32: aload 4
/* 1615:    */    //   34: iconst_1
/* 1616:    */    //   35: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 1617:    */    //   38: aload 4
/* 1618:    */    //   40: ldc 57
/* 1619:    */    //   42: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 1620:    */    //   45: aload 4
/* 1621:    */    //   47: aconst_null
/* 1622:    */    //   48: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 1623:    */    //   51: aload 4
/* 1624:    */    //   53: ldc_w 407
/* 1625:    */    //   56: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1626:    */    //   59: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1627:    */    //   62: aload 4
/* 1628:    */    //   64: ldc_w 415
/* 1629:    */    //   67: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1630:    */    //   70: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1631:    */    //   73: aload 4
/* 1632:    */    //   75: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 1633:    */    //   78: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 1634:    */    //   81: aload 4
/* 1635:    */    //   83: new 38	javax/xml/namespace/QName
/* 1636:    */    //   86: dup
/* 1637:    */    //   87: ldc 103
/* 1638:    */    //   89: ldc 156
/* 1639:    */    //   91: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 1640:    */    //   94: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 1641:    */    //   97: aload_0
/* 1642:    */    //   98: aload 4
/* 1643:    */    //   100: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 1644:    */    //   103: aload_0
/* 1645:    */    //   104: aload 4
/* 1646:    */    //   106: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 1647:    */    //   109: aload 4
/* 1648:    */    //   111: iconst_3
/* 1649:    */    //   112: anewarray 389	java/lang/Object
/* 1650:    */    //   115: dup
/* 1651:    */    //   116: iconst_0
/* 1652:    */    //   117: aload_1
/* 1653:    */    //   118: aastore
/* 1654:    */    //   119: dup
/* 1655:    */    //   120: iconst_1
/* 1656:    */    //   121: aload_2
/* 1657:    */    //   122: aastore
/* 1658:    */    //   123: dup
/* 1659:    */    //   124: iconst_2
/* 1660:    */    //   125: aload_3
/* 1661:    */    //   126: aastore
/* 1662:    */    //   127: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 1663:    */    //   130: astore 5
/* 1664:    */    //   132: aload 5
/* 1665:    */    //   134: instanceof 264
/* 1666:    */    //   137: ifeq +9 -> 146
/* 1667:    */    //   140: aload 5
/* 1668:    */    //   142: checkcast 264	java/rmi/RemoteException
/* 1669:    */    //   145: athrow
/* 1670:    */    //   146: aload_0
/* 1671:    */    //   147: aload 4
/* 1672:    */    //   149: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 1673:    */    //   152: aload 5
/* 1674:    */    //   154: checkcast 114	java/lang/Integer
/* 1675:    */    //   157: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1676:    */    //   160: ireturn
/* 1677:    */    //   161: astore 6
/* 1678:    */    //   163: aload 5
/* 1679:    */    //   165: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 1680:    */    //   168: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 1681:    */    //   171: checkcast 114	java/lang/Integer
/* 1682:    */    //   174: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1683:    */    //   177: ireturn
/* 1684:    */    //   178: astore 5
/* 1685:    */    //   180: aload 5
/* 1686:    */    //   182: athrow
/* 1687:    */    // Line number table:
/* 1688:    */    //   Java source line #715	-> byte code offset #0
/* 1689:    */    //   Java source line #716	-> byte code offset #7
/* 1690:    */    //   Java source line #718	-> byte code offset #15
/* 1691:    */    //   Java source line #719	-> byte code offset #21
/* 1692:    */    //   Java source line #720	-> byte code offset #32
/* 1693:    */    //   Java source line #721	-> byte code offset #38
/* 1694:    */    //   Java source line #722	-> byte code offset #45
/* 1695:    */    //   Java source line #723	-> byte code offset #51
/* 1696:    */    //   Java source line #724	-> byte code offset #62
/* 1697:    */    //   Java source line #725	-> byte code offset #73
/* 1698:    */    //   Java source line #726	-> byte code offset #81
/* 1699:    */    //   Java source line #728	-> byte code offset #97
/* 1700:    */    //   Java source line #729	-> byte code offset #103
/* 1701:    */    //   Java source line #730	-> byte code offset #109
/* 1702:    */    //   Java source line #732	-> byte code offset #132
/* 1703:    */    //   Java source line #733	-> byte code offset #140
/* 1704:    */    //   Java source line #736	-> byte code offset #146
/* 1705:    */    //   Java source line #738	-> byte code offset #152
/* 1706:    */    //   Java source line #739	-> byte code offset #161
/* 1707:    */    //   Java source line #740	-> byte code offset #163
/* 1708:    */    //   Java source line #743	-> byte code offset #178
/* 1709:    */    //   Java source line #744	-> byte code offset #180
/* 1710:    */    // Local variable table:
/* 1711:    */    //   start	length	slot	name	signature
/* 1712:    */    //   0	183	0	this	SDKServiceBindingStub
/* 1713:    */    //   0	183	1	arg0	String
/* 1714:    */    //   0	183	2	arg1	String
/* 1715:    */    //   0	183	3	arg2	String
/* 1716:    */    //   19	129	4	_call	org.apache.axis.client.Call
/* 1717:    */    //   130	34	5	_resp	Object
/* 1718:    */    //   178	3	5	axisFaultException	org.apache.axis.AxisFault
/* 1719:    */    //   161	3	6	_exception	java.lang.Exception
/* 1720:    */    // Exception table:
/* 1721:    */    //   from	to	target	type
/* 1722:    */    //   152	160	161	java/lang/Exception
/* 1723:    */    //   109	160	178	org/apache/axis/AxisFault
/* 1724:    */    //   161	177	178	org/apache/axis/AxisFault
/* 1725:    */  }
/* 1726:    */  
/* 1727:    */  /* Error */
/* 1728:    */  public int sendSMS(String arg0, String arg1, String arg2, String[] arg3, String arg4, String arg5, String arg6, int arg7, long arg8)
/* 1729:    */    throws RemoteException
/* 1730:    */  {
/* 1731:    */    // Byte code:
/* 1732:    */    //   0: aload_0
/* 1733:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 1734:    */    //   4: ifnonnull +11 -> 15
/* 1735:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 1736:    */    //   10: dup
/* 1737:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 1738:    */    //   14: athrow
/* 1739:    */    //   15: aload_0
/* 1740:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 1741:    */    //   19: astore 11
/* 1742:    */    //   21: aload 11
/* 1743:    */    //   23: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 1744:    */    //   26: bipush 10
/* 1745:    */    //   28: aaload
/* 1746:    */    //   29: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 1747:    */    //   32: aload 11
/* 1748:    */    //   34: iconst_1
/* 1749:    */    //   35: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 1750:    */    //   38: aload 11
/* 1751:    */    //   40: ldc 57
/* 1752:    */    //   42: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 1753:    */    //   45: aload 11
/* 1754:    */    //   47: aconst_null
/* 1755:    */    //   48: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 1756:    */    //   51: aload 11
/* 1757:    */    //   53: ldc_w 407
/* 1758:    */    //   56: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1759:    */    //   59: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1760:    */    //   62: aload 11
/* 1761:    */    //   64: ldc_w 415
/* 1762:    */    //   67: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1763:    */    //   70: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1764:    */    //   73: aload 11
/* 1765:    */    //   75: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 1766:    */    //   78: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 1767:    */    //   81: aload 11
/* 1768:    */    //   83: new 38	javax/xml/namespace/QName
/* 1769:    */    //   86: dup
/* 1770:    */    //   87: ldc 103
/* 1771:    */    //   89: ldc 162
/* 1772:    */    //   91: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 1773:    */    //   94: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 1774:    */    //   97: aload_0
/* 1775:    */    //   98: aload 11
/* 1776:    */    //   100: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 1777:    */    //   103: aload_0
/* 1778:    */    //   104: aload 11
/* 1779:    */    //   106: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 1780:    */    //   109: aload 11
/* 1781:    */    //   111: bipush 9
/* 1782:    */    //   113: anewarray 389	java/lang/Object
/* 1783:    */    //   116: dup
/* 1784:    */    //   117: iconst_0
/* 1785:    */    //   118: aload_1
/* 1786:    */    //   119: aastore
/* 1787:    */    //   120: dup
/* 1788:    */    //   121: iconst_1
/* 1789:    */    //   122: aload_2
/* 1790:    */    //   123: aastore
/* 1791:    */    //   124: dup
/* 1792:    */    //   125: iconst_2
/* 1793:    */    //   126: aload_3
/* 1794:    */    //   127: aastore
/* 1795:    */    //   128: dup
/* 1796:    */    //   129: iconst_3
/* 1797:    */    //   130: aload 4
/* 1798:    */    //   132: aastore
/* 1799:    */    //   133: dup
/* 1800:    */    //   134: iconst_4
/* 1801:    */    //   135: aload 5
/* 1802:    */    //   137: aastore
/* 1803:    */    //   138: dup
/* 1804:    */    //   139: iconst_5
/* 1805:    */    //   140: aload 6
/* 1806:    */    //   142: aastore
/* 1807:    */    //   143: dup
/* 1808:    */    //   144: bipush 6
/* 1809:    */    //   146: aload 7
/* 1810:    */    //   148: aastore
/* 1811:    */    //   149: dup
/* 1812:    */    //   150: bipush 7
/* 1813:    */    //   152: new 114	java/lang/Integer
/* 1814:    */    //   155: dup
/* 1815:    */    //   156: iload 8
/* 1816:    */    //   158: invokespecial 472	java/lang/Integer:<init>	(I)V
/* 1817:    */    //   161: aastore
/* 1818:    */    //   162: dup
/* 1819:    */    //   163: bipush 8
/* 1820:    */    //   165: new 172	java/lang/Long
/* 1821:    */    //   168: dup
/* 1822:    */    //   169: lload 9
/* 1823:    */    //   171: invokespecial 475	java/lang/Long:<init>	(J)V
/* 1824:    */    //   174: aastore
/* 1825:    */    //   175: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 1826:    */    //   178: astore 12
/* 1827:    */    //   180: aload 12
/* 1828:    */    //   182: instanceof 264
/* 1829:    */    //   185: ifeq +9 -> 194
/* 1830:    */    //   188: aload 12
/* 1831:    */    //   190: checkcast 264	java/rmi/RemoteException
/* 1832:    */    //   193: athrow
/* 1833:    */    //   194: aload_0
/* 1834:    */    //   195: aload 11
/* 1835:    */    //   197: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 1836:    */    //   200: aload 12
/* 1837:    */    //   202: checkcast 114	java/lang/Integer
/* 1838:    */    //   205: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1839:    */    //   208: ireturn
/* 1840:    */    //   209: astore 13
/* 1841:    */    //   211: aload 12
/* 1842:    */    //   213: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 1843:    */    //   216: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 1844:    */    //   219: checkcast 114	java/lang/Integer
/* 1845:    */    //   222: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1846:    */    //   225: ireturn
/* 1847:    */    //   226: astore 12
/* 1848:    */    //   228: aload 12
/* 1849:    */    //   230: athrow
/* 1850:    */    // Line number table:
/* 1851:    */    //   Java source line #749	-> byte code offset #0
/* 1852:    */    //   Java source line #750	-> byte code offset #7
/* 1853:    */    //   Java source line #752	-> byte code offset #15
/* 1854:    */    //   Java source line #753	-> byte code offset #21
/* 1855:    */    //   Java source line #754	-> byte code offset #32
/* 1856:    */    //   Java source line #755	-> byte code offset #38
/* 1857:    */    //   Java source line #756	-> byte code offset #45
/* 1858:    */    //   Java source line #757	-> byte code offset #51
/* 1859:    */    //   Java source line #758	-> byte code offset #62
/* 1860:    */    //   Java source line #759	-> byte code offset #73
/* 1861:    */    //   Java source line #760	-> byte code offset #81
/* 1862:    */    //   Java source line #762	-> byte code offset #97
/* 1863:    */    //   Java source line #763	-> byte code offset #103
/* 1864:    */    //   Java source line #764	-> byte code offset #109
/* 1865:    */    //   Java source line #766	-> byte code offset #180
/* 1866:    */    //   Java source line #767	-> byte code offset #188
/* 1867:    */    //   Java source line #770	-> byte code offset #194
/* 1868:    */    //   Java source line #772	-> byte code offset #200
/* 1869:    */    //   Java source line #773	-> byte code offset #209
/* 1870:    */    //   Java source line #774	-> byte code offset #211
/* 1871:    */    //   Java source line #777	-> byte code offset #226
/* 1872:    */    //   Java source line #778	-> byte code offset #228
/* 1873:    */    // Local variable table:
/* 1874:    */    //   start	length	slot	name	signature
/* 1875:    */    //   0	231	0	this	SDKServiceBindingStub
/* 1876:    */    //   0	231	1	arg0	String
/* 1877:    */    //   0	231	2	arg1	String
/* 1878:    */    //   0	231	3	arg2	String
/* 1879:    */    //   0	231	4	arg3	String[]
/* 1880:    */    //   0	231	5	arg4	String
/* 1881:    */    //   0	231	6	arg5	String
/* 1882:    */    //   0	231	7	arg6	String
/* 1883:    */    //   0	231	8	arg7	int
/* 1884:    */    //   0	231	9	arg8	long
/* 1885:    */    //   19	177	11	_call	org.apache.axis.client.Call
/* 1886:    */    //   178	34	12	_resp	Object
/* 1887:    */    //   226	3	12	axisFaultException	org.apache.axis.AxisFault
/* 1888:    */    //   209	3	13	_exception	java.lang.Exception
/* 1889:    */    // Exception table:
/* 1890:    */    //   from	to	target	type
/* 1891:    */    //   200	208	209	java/lang/Exception
/* 1892:    */    //   109	208	226	org/apache/axis/AxisFault
/* 1893:    */    //   209	225	226	org/apache/axis/AxisFault
/* 1894:    */  }
/* 1895:    */  
/* 1896:    */  /* Error */
/* 1897:    */  public int serialPwdUpd(String arg0, String arg1, String arg2, String arg3)
/* 1898:    */    throws RemoteException
/* 1899:    */  {
/* 1900:    */    // Byte code:
/* 1901:    */    //   0: aload_0
/* 1902:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 1903:    */    //   4: ifnonnull +11 -> 15
/* 1904:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 1905:    */    //   10: dup
/* 1906:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 1907:    */    //   14: athrow
/* 1908:    */    //   15: aload_0
/* 1909:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 1910:    */    //   19: astore 5
/* 1911:    */    //   21: aload 5
/* 1912:    */    //   23: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 1913:    */    //   26: bipush 11
/* 1914:    */    //   28: aaload
/* 1915:    */    //   29: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 1916:    */    //   32: aload 5
/* 1917:    */    //   34: iconst_1
/* 1918:    */    //   35: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 1919:    */    //   38: aload 5
/* 1920:    */    //   40: ldc 57
/* 1921:    */    //   42: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 1922:    */    //   45: aload 5
/* 1923:    */    //   47: aconst_null
/* 1924:    */    //   48: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 1925:    */    //   51: aload 5
/* 1926:    */    //   53: ldc_w 407
/* 1927:    */    //   56: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1928:    */    //   59: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1929:    */    //   62: aload 5
/* 1930:    */    //   64: ldc_w 415
/* 1931:    */    //   67: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 1932:    */    //   70: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 1933:    */    //   73: aload 5
/* 1934:    */    //   75: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 1935:    */    //   78: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 1936:    */    //   81: aload 5
/* 1937:    */    //   83: new 38	javax/xml/namespace/QName
/* 1938:    */    //   86: dup
/* 1939:    */    //   87: ldc 103
/* 1940:    */    //   89: ldc 174
/* 1941:    */    //   91: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 1942:    */    //   94: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 1943:    */    //   97: aload_0
/* 1944:    */    //   98: aload 5
/* 1945:    */    //   100: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 1946:    */    //   103: aload_0
/* 1947:    */    //   104: aload 5
/* 1948:    */    //   106: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 1949:    */    //   109: aload 5
/* 1950:    */    //   111: iconst_4
/* 1951:    */    //   112: anewarray 389	java/lang/Object
/* 1952:    */    //   115: dup
/* 1953:    */    //   116: iconst_0
/* 1954:    */    //   117: aload_1
/* 1955:    */    //   118: aastore
/* 1956:    */    //   119: dup
/* 1957:    */    //   120: iconst_1
/* 1958:    */    //   121: aload_2
/* 1959:    */    //   122: aastore
/* 1960:    */    //   123: dup
/* 1961:    */    //   124: iconst_2
/* 1962:    */    //   125: aload_3
/* 1963:    */    //   126: aastore
/* 1964:    */    //   127: dup
/* 1965:    */    //   128: iconst_3
/* 1966:    */    //   129: aload 4
/* 1967:    */    //   131: aastore
/* 1968:    */    //   132: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 1969:    */    //   135: astore 6
/* 1970:    */    //   137: aload 6
/* 1971:    */    //   139: instanceof 264
/* 1972:    */    //   142: ifeq +9 -> 151
/* 1973:    */    //   145: aload 6
/* 1974:    */    //   147: checkcast 264	java/rmi/RemoteException
/* 1975:    */    //   150: athrow
/* 1976:    */    //   151: aload_0
/* 1977:    */    //   152: aload 5
/* 1978:    */    //   154: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 1979:    */    //   157: aload 6
/* 1980:    */    //   159: checkcast 114	java/lang/Integer
/* 1981:    */    //   162: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1982:    */    //   165: ireturn
/* 1983:    */    //   166: astore 7
/* 1984:    */    //   168: aload 6
/* 1985:    */    //   170: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 1986:    */    //   173: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 1987:    */    //   176: checkcast 114	java/lang/Integer
/* 1988:    */    //   179: invokevirtual 459	java/lang/Integer:intValue	()I
/* 1989:    */    //   182: ireturn
/* 1990:    */    //   183: astore 6
/* 1991:    */    //   185: aload 6
/* 1992:    */    //   187: athrow
/* 1993:    */    // Line number table:
/* 1994:    */    //   Java source line #783	-> byte code offset #0
/* 1995:    */    //   Java source line #784	-> byte code offset #7
/* 1996:    */    //   Java source line #786	-> byte code offset #15
/* 1997:    */    //   Java source line #787	-> byte code offset #21
/* 1998:    */    //   Java source line #788	-> byte code offset #32
/* 1999:    */    //   Java source line #789	-> byte code offset #38
/* 2000:    */    //   Java source line #790	-> byte code offset #45
/* 2001:    */    //   Java source line #791	-> byte code offset #51
/* 2002:    */    //   Java source line #792	-> byte code offset #62
/* 2003:    */    //   Java source line #793	-> byte code offset #73
/* 2004:    */    //   Java source line #794	-> byte code offset #81
/* 2005:    */    //   Java source line #796	-> byte code offset #97
/* 2006:    */    //   Java source line #797	-> byte code offset #103
/* 2007:    */    //   Java source line #798	-> byte code offset #109
/* 2008:    */    //   Java source line #800	-> byte code offset #137
/* 2009:    */    //   Java source line #801	-> byte code offset #145
/* 2010:    */    //   Java source line #804	-> byte code offset #151
/* 2011:    */    //   Java source line #806	-> byte code offset #157
/* 2012:    */    //   Java source line #807	-> byte code offset #166
/* 2013:    */    //   Java source line #808	-> byte code offset #168
/* 2014:    */    //   Java source line #811	-> byte code offset #183
/* 2015:    */    //   Java source line #812	-> byte code offset #185
/* 2016:    */    // Local variable table:
/* 2017:    */    //   start	length	slot	name	signature
/* 2018:    */    //   0	188	0	this	SDKServiceBindingStub
/* 2019:    */    //   0	188	1	arg0	String
/* 2020:    */    //   0	188	2	arg1	String
/* 2021:    */    //   0	188	3	arg2	String
/* 2022:    */    //   0	188	4	arg3	String
/* 2023:    */    //   19	134	5	_call	org.apache.axis.client.Call
/* 2024:    */    //   135	34	6	_resp	Object
/* 2025:    */    //   183	3	6	axisFaultException	org.apache.axis.AxisFault
/* 2026:    */    //   166	3	7	_exception	java.lang.Exception
/* 2027:    */    // Exception table:
/* 2028:    */    //   from	to	target	type
/* 2029:    */    //   157	165	166	java/lang/Exception
/* 2030:    */    //   109	165	183	org/apache/axis/AxisFault
/* 2031:    */    //   166	182	183	org/apache/axis/AxisFault
/* 2032:    */  }
/* 2033:    */  
/* 2034:    */  /* Error */
/* 2035:    */  public int setMOForward(String arg0, String arg1, String arg2)
/* 2036:    */    throws RemoteException
/* 2037:    */  {
/* 2038:    */    // Byte code:
/* 2039:    */    //   0: aload_0
/* 2040:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 2041:    */    //   4: ifnonnull +11 -> 15
/* 2042:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 2043:    */    //   10: dup
/* 2044:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 2045:    */    //   14: athrow
/* 2046:    */    //   15: aload_0
/* 2047:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 2048:    */    //   19: astore 4
/* 2049:    */    //   21: aload 4
/* 2050:    */    //   23: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 2051:    */    //   26: bipush 12
/* 2052:    */    //   28: aaload
/* 2053:    */    //   29: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 2054:    */    //   32: aload 4
/* 2055:    */    //   34: iconst_1
/* 2056:    */    //   35: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 2057:    */    //   38: aload 4
/* 2058:    */    //   40: ldc 57
/* 2059:    */    //   42: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 2060:    */    //   45: aload 4
/* 2061:    */    //   47: aconst_null
/* 2062:    */    //   48: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 2063:    */    //   51: aload 4
/* 2064:    */    //   53: ldc_w 407
/* 2065:    */    //   56: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 2066:    */    //   59: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 2067:    */    //   62: aload 4
/* 2068:    */    //   64: ldc_w 415
/* 2069:    */    //   67: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 2070:    */    //   70: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 2071:    */    //   73: aload 4
/* 2072:    */    //   75: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 2073:    */    //   78: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 2074:    */    //   81: aload 4
/* 2075:    */    //   83: new 38	javax/xml/namespace/QName
/* 2076:    */    //   86: dup
/* 2077:    */    //   87: ldc 103
/* 2078:    */    //   89: ldc 176
/* 2079:    */    //   91: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 2080:    */    //   94: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 2081:    */    //   97: aload_0
/* 2082:    */    //   98: aload 4
/* 2083:    */    //   100: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 2084:    */    //   103: aload_0
/* 2085:    */    //   104: aload 4
/* 2086:    */    //   106: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 2087:    */    //   109: aload 4
/* 2088:    */    //   111: iconst_3
/* 2089:    */    //   112: anewarray 389	java/lang/Object
/* 2090:    */    //   115: dup
/* 2091:    */    //   116: iconst_0
/* 2092:    */    //   117: aload_1
/* 2093:    */    //   118: aastore
/* 2094:    */    //   119: dup
/* 2095:    */    //   120: iconst_1
/* 2096:    */    //   121: aload_2
/* 2097:    */    //   122: aastore
/* 2098:    */    //   123: dup
/* 2099:    */    //   124: iconst_2
/* 2100:    */    //   125: aload_3
/* 2101:    */    //   126: aastore
/* 2102:    */    //   127: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 2103:    */    //   130: astore 5
/* 2104:    */    //   132: aload 5
/* 2105:    */    //   134: instanceof 264
/* 2106:    */    //   137: ifeq +9 -> 146
/* 2107:    */    //   140: aload 5
/* 2108:    */    //   142: checkcast 264	java/rmi/RemoteException
/* 2109:    */    //   145: athrow
/* 2110:    */    //   146: aload_0
/* 2111:    */    //   147: aload 4
/* 2112:    */    //   149: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 2113:    */    //   152: aload 5
/* 2114:    */    //   154: checkcast 114	java/lang/Integer
/* 2115:    */    //   157: invokevirtual 459	java/lang/Integer:intValue	()I
/* 2116:    */    //   160: ireturn
/* 2117:    */    //   161: astore 6
/* 2118:    */    //   163: aload 5
/* 2119:    */    //   165: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 2120:    */    //   168: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 2121:    */    //   171: checkcast 114	java/lang/Integer
/* 2122:    */    //   174: invokevirtual 459	java/lang/Integer:intValue	()I
/* 2123:    */    //   177: ireturn
/* 2124:    */    //   178: astore 5
/* 2125:    */    //   180: aload 5
/* 2126:    */    //   182: athrow
/* 2127:    */    // Line number table:
/* 2128:    */    //   Java source line #817	-> byte code offset #0
/* 2129:    */    //   Java source line #818	-> byte code offset #7
/* 2130:    */    //   Java source line #820	-> byte code offset #15
/* 2131:    */    //   Java source line #821	-> byte code offset #21
/* 2132:    */    //   Java source line #822	-> byte code offset #32
/* 2133:    */    //   Java source line #823	-> byte code offset #38
/* 2134:    */    //   Java source line #824	-> byte code offset #45
/* 2135:    */    //   Java source line #825	-> byte code offset #51
/* 2136:    */    //   Java source line #826	-> byte code offset #62
/* 2137:    */    //   Java source line #827	-> byte code offset #73
/* 2138:    */    //   Java source line #828	-> byte code offset #81
/* 2139:    */    //   Java source line #830	-> byte code offset #97
/* 2140:    */    //   Java source line #831	-> byte code offset #103
/* 2141:    */    //   Java source line #832	-> byte code offset #109
/* 2142:    */    //   Java source line #834	-> byte code offset #132
/* 2143:    */    //   Java source line #835	-> byte code offset #140
/* 2144:    */    //   Java source line #838	-> byte code offset #146
/* 2145:    */    //   Java source line #840	-> byte code offset #152
/* 2146:    */    //   Java source line #841	-> byte code offset #161
/* 2147:    */    //   Java source line #842	-> byte code offset #163
/* 2148:    */    //   Java source line #845	-> byte code offset #178
/* 2149:    */    //   Java source line #846	-> byte code offset #180
/* 2150:    */    // Local variable table:
/* 2151:    */    //   start	length	slot	name	signature
/* 2152:    */    //   0	183	0	this	SDKServiceBindingStub
/* 2153:    */    //   0	183	1	arg0	String
/* 2154:    */    //   0	183	2	arg1	String
/* 2155:    */    //   0	183	3	arg2	String
/* 2156:    */    //   19	129	4	_call	org.apache.axis.client.Call
/* 2157:    */    //   130	34	5	_resp	Object
/* 2158:    */    //   178	3	5	axisFaultException	org.apache.axis.AxisFault
/* 2159:    */    //   161	3	6	_exception	java.lang.Exception
/* 2160:    */    // Exception table:
/* 2161:    */    //   from	to	target	type
/* 2162:    */    //   152	160	161	java/lang/Exception
/* 2163:    */    //   109	160	178	org/apache/axis/AxisFault
/* 2164:    */    //   161	177	178	org/apache/axis/AxisFault
/* 2165:    */  }
/* 2166:    */  
/* 2167:    */  /* Error */
/* 2168:    */  public int setMOForwardEx(String arg0, String arg1, String[] arg2)
/* 2169:    */    throws RemoteException
/* 2170:    */  {
/* 2171:    */    // Byte code:
/* 2172:    */    //   0: aload_0
/* 2173:    */    //   1: getfield 189	org/apache/axis/client/Stub:cachedEndpoint	Ljava/net/URL;
/* 2174:    */    //   4: ifnonnull +11 -> 15
/* 2175:    */    //   7: new 392	org/apache/axis/NoEndPointException
/* 2176:    */    //   10: dup
/* 2177:    */    //   11: invokespecial 394	org/apache/axis/NoEndPointException:<init>	()V
/* 2178:    */    //   14: athrow
/* 2179:    */    //   15: aload_0
/* 2180:    */    //   16: invokevirtual 395	com/soofound/protocol/emay/SDKServiceBindingStub:createCall	()Lorg/apache/axis/client/Call;
/* 2181:    */    //   19: astore 4
/* 2182:    */    //   21: aload 4
/* 2183:    */    //   23: getstatic 19	com/soofound/protocol/emay/SDKServiceBindingStub:_operations	[Lorg/apache/axis/description/OperationDesc;
/* 2184:    */    //   26: bipush 13
/* 2185:    */    //   28: aaload
/* 2186:    */    //   29: invokevirtual 397	org/apache/axis/client/Call:setOperation	(Lorg/apache/axis/description/OperationDesc;)V
/* 2187:    */    //   32: aload 4
/* 2188:    */    //   34: iconst_1
/* 2189:    */    //   35: invokevirtual 401	org/apache/axis/client/Call:setUseSOAPAction	(Z)V
/* 2190:    */    //   38: aload 4
/* 2191:    */    //   40: ldc 57
/* 2192:    */    //   42: invokevirtual 404	org/apache/axis/client/Call:setSOAPActionURI	(Ljava/lang/String;)V
/* 2193:    */    //   45: aload 4
/* 2194:    */    //   47: aconst_null
/* 2195:    */    //   48: invokevirtual 343	org/apache/axis/client/Call:setEncodingStyle	(Ljava/lang/String;)V
/* 2196:    */    //   51: aload 4
/* 2197:    */    //   53: ldc_w 407
/* 2198:    */    //   56: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 2199:    */    //   59: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 2200:    */    //   62: aload 4
/* 2201:    */    //   64: ldc_w 415
/* 2202:    */    //   67: getstatic 409	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
/* 2203:    */    //   70: invokevirtual 332	org/apache/axis/client/Call:setProperty	(Ljava/lang/String;Ljava/lang/Object;)V
/* 2204:    */    //   73: aload 4
/* 2205:    */    //   75: getstatic 417	org/apache/axis/soap/SOAPConstants:SOAP11_CONSTANTS	Lorg/apache/axis/soap/SOAP11Constants;
/* 2206:    */    //   78: invokevirtual 423	org/apache/axis/client/Call:setSOAPVersion	(Lorg/apache/axis/soap/SOAPConstants;)V
/* 2207:    */    //   81: aload 4
/* 2208:    */    //   83: new 38	javax/xml/namespace/QName
/* 2209:    */    //   86: dup
/* 2210:    */    //   87: ldc 103
/* 2211:    */    //   89: ldc 178
/* 2212:    */    //   91: invokespecial 44	javax/xml/namespace/QName:<init>	(Ljava/lang/String;Ljava/lang/String;)V
/* 2213:    */    //   94: invokevirtual 427	org/apache/axis/client/Call:setOperationName	(Ljavax/xml/namespace/QName;)V
/* 2214:    */    //   97: aload_0
/* 2215:    */    //   98: aload 4
/* 2216:    */    //   100: invokevirtual 430	com/soofound/protocol/emay/SDKServiceBindingStub:setRequestHeaders	(Lorg/apache/axis/client/Call;)V
/* 2217:    */    //   103: aload_0
/* 2218:    */    //   104: aload 4
/* 2219:    */    //   106: invokevirtual 434	com/soofound/protocol/emay/SDKServiceBindingStub:setAttachments	(Lorg/apache/axis/client/Call;)V
/* 2220:    */    //   109: aload 4
/* 2221:    */    //   111: iconst_3
/* 2222:    */    //   112: anewarray 389	java/lang/Object
/* 2223:    */    //   115: dup
/* 2224:    */    //   116: iconst_0
/* 2225:    */    //   117: aload_1
/* 2226:    */    //   118: aastore
/* 2227:    */    //   119: dup
/* 2228:    */    //   120: iconst_1
/* 2229:    */    //   121: aload_2
/* 2230:    */    //   122: aastore
/* 2231:    */    //   123: dup
/* 2232:    */    //   124: iconst_2
/* 2233:    */    //   125: aload_3
/* 2234:    */    //   126: aastore
/* 2235:    */    //   127: invokevirtual 437	org/apache/axis/client/Call:invoke	([Ljava/lang/Object;)Ljava/lang/Object;
/* 2236:    */    //   130: astore 5
/* 2237:    */    //   132: aload 5
/* 2238:    */    //   134: instanceof 264
/* 2239:    */    //   137: ifeq +9 -> 146
/* 2240:    */    //   140: aload 5
/* 2241:    */    //   142: checkcast 264	java/rmi/RemoteException
/* 2242:    */    //   145: athrow
/* 2243:    */    //   146: aload_0
/* 2244:    */    //   147: aload 4
/* 2245:    */    //   149: invokevirtual 441	com/soofound/protocol/emay/SDKServiceBindingStub:extractAttachments	(Lorg/apache/axis/client/Call;)V
/* 2246:    */    //   152: aload 5
/* 2247:    */    //   154: checkcast 114	java/lang/Integer
/* 2248:    */    //   157: invokevirtual 459	java/lang/Integer:intValue	()I
/* 2249:    */    //   160: ireturn
/* 2250:    */    //   161: astore 6
/* 2251:    */    //   163: aload 5
/* 2252:    */    //   165: getstatic 113	java/lang/Integer:TYPE	Ljava/lang/Class;
/* 2253:    */    //   168: invokestatic 444	org/apache/axis/utils/JavaUtils:convert	(Ljava/lang/Object;Ljava/lang/Class;)Ljava/lang/Object;
/* 2254:    */    //   171: checkcast 114	java/lang/Integer
/* 2255:    */    //   174: invokevirtual 459	java/lang/Integer:intValue	()I
/* 2256:    */    //   177: ireturn
/* 2257:    */    //   178: astore 5
/* 2258:    */    //   180: aload 5
/* 2259:    */    //   182: athrow
/* 2260:    */    // Line number table:
/* 2261:    */    //   Java source line #851	-> byte code offset #0
/* 2262:    */    //   Java source line #852	-> byte code offset #7
/* 2263:    */    //   Java source line #854	-> byte code offset #15
/* 2264:    */    //   Java source line #855	-> byte code offset #21
/* 2265:    */    //   Java source line #856	-> byte code offset #32
/* 2266:    */    //   Java source line #857	-> byte code offset #38
/* 2267:    */    //   Java source line #858	-> byte code offset #45
/* 2268:    */    //   Java source line #859	-> byte code offset #51
/* 2269:    */    //   Java source line #860	-> byte code offset #62
/* 2270:    */    //   Java source line #861	-> byte code offset #73
/* 2271:    */    //   Java source line #862	-> byte code offset #81
/* 2272:    */    //   Java source line #864	-> byte code offset #97
/* 2273:    */    //   Java source line #865	-> byte code offset #103
/* 2274:    */    //   Java source line #866	-> byte code offset #109
/* 2275:    */    //   Java source line #868	-> byte code offset #132
/* 2276:    */    //   Java source line #869	-> byte code offset #140
/* 2277:    */    //   Java source line #872	-> byte code offset #146
/* 2278:    */    //   Java source line #874	-> byte code offset #152
/* 2279:    */    //   Java source line #875	-> byte code offset #161
/* 2280:    */    //   Java source line #876	-> byte code offset #163
/* 2281:    */    //   Java source line #879	-> byte code offset #178
/* 2282:    */    //   Java source line #880	-> byte code offset #180
/* 2283:    */    // Local variable table:
/* 2284:    */    //   start	length	slot	name	signature
/* 2285:    */    //   0	183	0	this	SDKServiceBindingStub
/* 2286:    */    //   0	183	1	arg0	String
/* 2287:    */    //   0	183	2	arg1	String
/* 2288:    */    //   0	183	3	arg2	String[]
/* 2289:    */    //   19	129	4	_call	org.apache.axis.client.Call
/* 2290:    */    //   130	34	5	_resp	Object
/* 2291:    */    //   178	3	5	axisFaultException	org.apache.axis.AxisFault
/* 2292:    */    //   161	3	6	_exception	java.lang.Exception
/* 2293:    */    // Exception table:
/* 2294:    */    //   from	to	target	type
/* 2295:    */    //   152	160	161	java/lang/Exception
/* 2296:    */    //   109	160	178	org/apache/axis/AxisFault
/* 2297:    */    //   161	177	178	org/apache/axis/AxisFault
/* 2298:    */  }
/* 2299:    */}


/* Location:           D:\cache\windows\Desktop\ac\WEB-INF\classes\
 * Qualified Name:     com.soofound.protocol.emay.SDKServiceBindingStub
 * JD-Core Version:    0.7.0-SNAPSHOT-20130630
 */