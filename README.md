### 创建时间：2021-01-07 14:42:17

* 1.集成音乐后台 2021-01-11 13:51:10

* 2.框架说明
    > 1.maven-client -->  公共依赖配置pox( 添加 jar包 依赖) <br><br>
    &nbsp;&nbsp;&nbsp;&nbsp; _org.springframework.cloud - **spring-cloud-dependencies**_ <br>
    &nbsp;&nbsp;&nbsp;&nbsp; _org.springframework.boot - spring-boot-starter-web_ <br>
    &nbsp;&nbsp;&nbsp;&nbsp; _org.springframework.boot - spring-boot-starter-test_ <br>
    &nbsp;&nbsp;&nbsp;&nbsp; _org.projectlombok - lombok_ <br>
    &nbsp;&nbsp;&nbsp;&nbsp; _com.alibaba - fastjson_ <br>
    > 
    > 2.maven-web --> web依赖（例如：）
    > 
    > 3.framework-core --> 公共类 
  
* 3.插件说明
  > 1.plantuml (画图软件)<br/>
  > &nbsp;&nbsp;&nbsp;&nbsp;指导文件：http://plantuml.com/zh/guide
  > 
* 4.证书相关
  ####4.1 加密策略
  > 1.随机生成AES密钥key -- aesKey </br>
  > 2.AES加密明文 -- AES (message) = byte[] </br>
  > 3.RSA公钥加密aesKey -- RSA (aesKey) = byte[] </br>
  > 4.拼接：RSA(aesKey) + AES(message) = byte[] </br>
  ####4.2 生成证书命令
  > keytool -genkey -alias RECORD_ACCOUNT -keyalg RSA -keystore record_account.jks -keysize 2048 -sigalg sha256withrsa -validity 36500 -storepass ra123456 -keypass ra123456 -dname "CN=RECORD_ACCOUNT,OU=LifeCode,O=LifeCode,L=GuangDong,ST=ShenZhen,C=ZH"

* 5 项目中遇到的问题
  ####5.1 请求时间问题
  > 未指定时区时，默认指定时区非中国时区，导致插入数据时间不对，解决方案，在配置mysql url 地方添加：serverTimezone=Asia/Shanghai <br/>
  > datasource: <br/>
  >&nbsp;&nbsp;driver-class-name: com.mysql.cj.jdbc.Driver #mysql驱动 <br/>
  >&nbsp;&nbsp;url: jdbc:mysql://localhost:3306/record_account?zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai <br/>
  >&nbsp;&nbsp;username: root <br/>
  >&nbsp;&nbsp;password: root123456 <br/>
  ####5.2 提交Git代码时报错
  >报错内容：<br/>
  > fatal: unable to access 'https://github.com/luolin2611/life-code/': LibreSSL SSL_connect: SSL_ERROR_SYSCALL in connection to github.com:443<br/>
  > 解决方案：<br/>
  > 找到代理的端口号：
  > ![img_1.png](img_1.png)<br/>
  > 执行命令：
  > git config --global --add remote.origin.proxy "127.0.0.1:7890"

* 6 其它
  ####6.1 生成banner
  > 地址： https://www.bootschool.net/ascii
  > 选项:  3d-ascii
  #### 6.2 打包相关
  > mvn clean package -P sit1
  > 
