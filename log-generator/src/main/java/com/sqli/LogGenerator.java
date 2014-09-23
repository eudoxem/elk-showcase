package com.sqli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Generates logs to mimic monitored system.
 */
public class LogGenerator
{

    public static void main(String[] args)
    {
	final Logger logger = LoggerFactory.getLogger(LogGenerator.class);
	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String[] nodes = new String[]{ "evl240001", "evl240002", "evl240003", "evl240004" };
	String[] services = new String[]{ "QueueServiceImpl.create", "DocumentServiceImpl.createTasks",
		"DocumentServiceImpl.importFiles", "AbstractExecutionTriggerImpl.execute", "DocumentServiceImpl.toVo",
		"DocumentServiceImpl.retrieveEscalatedDocuments", "NotificationServiceImpl.checkForNotifications",
		"NotificationExecutionTriggerImpl.executeForTenant", "NotificationServiceImpl.sendReminderWarning",
		"NotificationServiceImpl.sendRemindWarning", "BasicUserServiceImpl.retrieveByIndex",
		"NotificationServiceImpl.retrieveUserQuietly", "NotificationServiceImpl.putUserDocuments",
		"NotificationServiceImpl.buildUserDocumentsMap", "BasicUserServiceImpl.retrieveCurrentUser",
		"NotificationServiceImpl.sendDailyNotification" };
	String[] id1  = new String[]{"f1","f2","f3","f4","f5"};
	String[] id2  = new String[]{"PROCESSED","CANCELLED","FAILED"};


	Random randomGenerator = new Random();


	for (int i = 0; i < 100000; i++)
	{
	    final String dateString = dateFormat.format(new Date());
	    final int randomInt0 = randomGenerator.nextInt(50000)+100;
	    final int randomInt1 = randomGenerator.nextInt(15);
	    final int randomInt2 = randomGenerator.nextInt(3);
	    final int randomInt3 = randomGenerator.nextInt(4);
	    final int randomInt4 = randomGenerator.nextInt(2);
	    final int randomInt5 = randomGenerator.nextInt(9)+1;

	    final String randomService = services[randomInt1];
	    final String randomNode = nodes[randomInt2];
	    final String randomFeature = id1[randomInt3];
	    final String randomOutcome = id2[randomInt4];

	    final String message =
		    String.format("[thread-%d] %s %s %s %s %s %d", randomInt5, dateString, randomService, randomNode,
			    randomFeature, randomOutcome, randomInt0);

	    logger.info(message);

	}

    }
}
