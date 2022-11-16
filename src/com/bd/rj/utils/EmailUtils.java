package com.bd.rj.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;



/**
 * @desc   发送邮件的工具类
 * @author ACER
 * @time   2022-01-06
 */
public class EmailUtils {
	
	//发送者自己的邮箱
	public static String sendEmailAccount;
	//邮箱授权码
	public static String sendEmailPwd;
	
	static
	{
		try 
		{
			Map<String, Object> map = PropertyUtils.getPropertyInfo("mail.properties");
			sendEmailAccount = (String) map.get("emailFrom");
			sendEmailPwd = (String) map.get("emialFromAuthorization");
		} 
		catch (Exception e) 
		{
			System.out.println("mail.properties获取属性配置文件中的内容异常，且异常信息为:"+e.getMessage());
		}
	}
	
	//收件人邮箱
	public static String receiveMailAccount="3206725624@qq.com";



/**
 * @desc  保存和发送
 * @param message
 * @param session
 * @throws MessagingException 
 */
private static void saveAndSendEmail(MimeMessage message, Session session) throws MessagingException 
{
	// 8. 设置发送时间
	message.setSentDate(new Date());
	
	// 9. 保存设置
	message.saveChanges();
	
	// 10. 根据 Session 获取邮件传输对象
	Transport transport = session.getTransport();
	
	//11.连接SMTP服务器
	transport.connect(sendEmailAccount, sendEmailPwd);
	
	// 12. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
	transport.sendMessage(message, message.getAllRecipients());
	
	  // 13. 关闭连接
	transport.close();	
}

/**
 * @desc  0.将发送邮件的前3步抽取成一个函数，目的是方便后续的代码的复用
 * @return
 */
private static Session getSession() 
{
	//1.创建参数配置, 用于连接邮件服务器的参数配置
		Properties props=new Properties();
	    props.setProperty("mail.transport.protocol","smtp");// 使用的协议（JavaMail规范要求）
	    props.setProperty("mail.smtp.host","smtp.163.com"); // 发件人的邮箱的 SMTP 服务器地址
	    props.setProperty("mail.smtp.auth", "true");    // 需要请求认证
		
		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session=Session.getInstance(props);
		session.setDebug(true);//以debug的模式进行运行，方便后续出错误时候的调试
		return session;
}

/**
 * @desc  3.发送带有图片+文字的邮件
 * @param receiver
 * @param emailSubject
 * @param emailText
 * @param imageUrl
 * @throws MessagingException 
 * @throws UnsupportedEncodingException 
 */
public static void createMineMessage(String receiver, String emailSubject, String emailText, String imageUrl) throws UnsupportedEncodingException, MessagingException {
	Session  session=EmailUtils.getSession();
	// 3. 创建一封邮件
    MimeMessage message = new MimeMessage(session);

    // 4. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
    message.setFrom(new InternetAddress(sendEmailAccount, "发件人的昵称", "UTF-8"));

    // 5. To: 收件人（可以增加多个收件人、抄送、密送）
    //    CC:抄送人，BCC:密送
    message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiver, "XX用户", "UTF-8"));

    // 6. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
    message.setSubject(emailSubject, "UTF-8");
    
    // 7. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
	MimeBodyPart txt = new MimeBodyPart();
	//要显示图片就用content不要用text          datahandler指向的资源 要加上cid:前缀 不然不显示
	txt.setContent(""+emailText+":这是一张图片\n<img src='cid:c.png' />","text/html;charset=UTF-8");
	
	MimeBodyPart img = new MimeBodyPart();
	DataHandler dh = new DataHandler(new FileDataSource(imageUrl));
	img.setDataHandler(dh);
	img.setContentID("c.png");
	
	MimeMultipart multipart = new MimeMultipart();
	multipart.addBodyPart(txt);
	multipart.addBodyPart(img);
	multipart.setSubType("related");
	
	message.setContent(multipart);
    
	//发送
    saveAndSendEmail(message, session);
    
}

/**
 * @desc  发送带有附件的邮件
 * @param receiver
 * @param emailSubject
 * @param emailText
 * @param fileUrl
 * @throws MessagingException 
 * @throws UnsupportedEncodingException 
 */
public static void createMineMessage2(String receiver, String emailSubject, String emailText, String fileUrl) throws MessagingException, UnsupportedEncodingException {
	 //1.创建session会话对象
	 Session session = getSession();
	 //2.创建一封邮件
  MimeMessage message=new MimeMessage(session);
  
  //3.设定发件人
  message.setFrom(new InternetAddress(sendEmailAccount, "发送者的昵称", "utf-8"));
	
  //4.设定接收人
  message.setRecipient(RecipientType.TO, new InternetAddress(receiver, "接收人", "utf-8"));
  
  //5.设定主题
  message.setSubject(emailSubject,"UTF-8");  
	
  // 7. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
   MimeBodyPart txt = new MimeBodyPart();
   txt.setContent(emailText, "text/html;charset=UTF-8");
	  
	  MimeBodyPart attachment = new MimeBodyPart();
  // 读取本地文件
  DataHandler dh2 = new DataHandler(new FileDataSource(fileUrl));
  // 将附件数据添加到"节点"
  attachment.setDataHandler(dh2);
  // 设置附件的文件名（需要编码）
  attachment.setFileName(MimeUtility.encodeText(dh2.getName()));       
   
  // 10. 设置（文本+图片）和 附件 的关系（合成一个大的混合"节点" / Multipart ）
  MimeMultipart mm = new MimeMultipart();
  mm.addBodyPart(txt);
  mm.addBodyPart(attachment);     // 如果有多个附件，可以创建多个多次添加
  mm.setSubType("mixed");         // 混合关系

  // 11. 设置整个邮件的关系（将最终的混合"节点"作为邮件的内容添加到邮件对象）
  message.setContent(mm);

  //12.保存和发送邮件
  saveAndSendEmail(message, session);
	
	
}

public static void createMineMessage(HttpServletRequest request, String email, String ucode) throws UnsupportedEncodingException, MessagingException {
	//0.将发送邮件的前3步抽取成一个函数，目的是方便后续的代码的复用
			Session session = EmailUtils.getSession();
			
			//3、创建一封邮件（可以简单理解为是那封信）
			MimeMessage message = new MimeMessage(session);

			//4、from：发件人
			message.setFrom(new InternetAddress(sendEmailAccount, "汽车租赁后台管理系统", "utf-8"));

			//5、 To: 收件人（可以增加多个收件人、抄送、密送）
		    //   CC:抄送人，BCC:密送
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email, "XX用户", "utf-8"));
			
			// 6. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
			message.setSubject("这个是邮件的主题：汽车租赁后台管理系统管理员密码", "utf-8");
			
		    // 7. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
			String context = "尊敬的用户，欢迎注册汽车租赁后台管理系统的管理员，你的登录密码为："+ucode+"</div>";
			message.setContent(context, "text/html;charset=utf-8");
			
			//8、保存和发送
		    saveAndSendEmail(message, session);
		
	
}


	
}
