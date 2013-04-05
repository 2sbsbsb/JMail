JMail - Java Mail Utility 
=====


Simple way to send email. 


Dependencies: Javax mail package. All libs are included. 



Usage: 

String FROM_ADDRESS = <ENTER_YOUR_EMAIL_ADDRESS>;

String FROM_PASSWORD = <ENTER_YOUR_PASSWORD>;

// I have added GOOGLE MAIL HOST by default but you are free to change it. 

String MAIL_HOST_ADDRESS = EmailServices.GOOGLE_MAIL_HOST;

int port = EmailServices.GOOGLE_MAIL_PORT


// Create eamil service instances

EmailServices emailServices = new EmailServices(MAIL_HOST_ADDRESS, port , FROM_ADDRESS,
FROM_PASSWORD);

// Details 

String toAddress = new String[] {Enter email addresses to which you want to sent email ?}

// Include ccAddress (optional) 

String ccAddress = null; 

String msgSubject = "Test Message";

String msgBody = "Test Message Body";

// Include attachments if needed which should be the path of the file. 

String[] attachmentPaths =  null; 

// Send mail

emailServices.sendMail(toAddresses, ccAddresses, msgSubject, msgBody, attachmentPaths);

It is as simple as that. 

