package PracticeQuestions.NotificationSystem;

import java.time.Instant;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

//Notification creation
interface INotification{
    public String getContent();
}

class SimpleNotification implements INotification{
    private final String text;

    SimpleNotification(String text){
        this.text = text;
    }

    @Override
    public String getContent(){
        return text;
    }
}

//Decorators for notification
abstract class INotificationDecorator implements INotification{
    public final INotification notification;

    INotificationDecorator(INotification notification){
        this.notification = notification;
    }

}

class TimeStampNotificationDecorator extends INotificationDecorator{
//    private final Instant instant = Instant.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "dd-MM-yyyy HH:mm:ss");

    TimeStampNotificationDecorator(INotification notification){
        super(notification);
    }

    @Override
    public String getContent() {
        return "[" +  LocalDateTime.now().format(formatter)  +  "] : " + notification.getContent();
    }
}

class SignatureNotificationDecorator extends INotificationDecorator{
    private final String signature;

    SignatureNotificationDecorator(INotification notification , String signature){
        super(notification);
        this.signature = signature;
    }

    @Override
    public String getContent() {
        return notification.getContent() +  "\n-- " + signature + "\n\n";
    }
}

//Observable
interface IObservable{
    public void addObserver(IObserver obs);
    public void removeObserver(IObserver obs);
    public void notifyObservers();
}

//concrete Observable
class NotificationObservable implements IObservable{
    private final List<IObserver> observers;
    private INotification currentNotification;

    NotificationObservable() {
        this.observers = new ArrayList<IObserver>();
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers){
            observer.update(this);
        }
    }

    public void setContent(INotification notification) {
        this.currentNotification = notification;
        notifyObservers();
    }

    public INotification getNotification(){
        return currentNotification;
    }

    public String getNotificationContent(){
        return currentNotification.getContent();
    }
}

//Observers
interface IObserver{
    public void update(NotificationObservable notificationObservable);
}

//i think logger can be made a singleton instance
//concrete observer;
class Logger implements IObserver{
    @Override
    public void update(NotificationObservable  observable){
        System.out.println("Logging new Notification : \n" + observable.getNotificationContent() );
    }
}


//Strategy for Notification Systems
interface INotificationStrategy{
    public void sendNotification(String content);
}

//concrete class of strategy
class SmsNotificationStrategy implements INotificationStrategy{
    private final String mobileNumber;

    SmsNotificationStrategy(String mobileNumber){
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void sendNotification(String content){
        System.out.println("Sending SMS Notification to: " + mobileNumber + "\n" + content);
    }
}

class EmailNotificationStrategy implements INotificationStrategy{
    private final String emailId;

    EmailNotificationStrategy(String emailId){
        this.emailId = emailId;
    }

    @Override
    public void sendNotification(String content) {
        System.out.println("Sending Email Notification to: " + emailId + "\n" + content);
    }
}

class PopUpNotificationStrategy implements INotificationStrategy{
    @Override
    public void sendNotification(String content) {
        System.out.println("Sending PopUp Notification :" +  content);
    }
}


//Another conceret observer class
class NotificationEngine implements IObserver{
    private final List<INotificationStrategy> notifyStrategies = new ArrayList<>();

    public void addNotificationStrategy(INotificationStrategy notificationStrategy){
        notifyStrategies.add(notificationStrategy);
    }

    public void removeNotificationStrategy(INotificationStrategy notificationStrategy){
        notifyStrategies.remove(notificationStrategy);
    }

    @Override
    public void update(NotificationObservable observable){
        for(INotificationStrategy notificationStrategy : notifyStrategies){
            notificationStrategy.sendNotification(observable.getNotificationContent());
        }
    }
}

//Notification Service - connects the notification content to the actual notificationObservable
//making this a singleton class-as only one instance of it can be reused everywhere needed
class NotificationService{
    private final List<INotification> notificationsHistory = new ArrayList<>();
    private NotificationObservable observable;
    private static NotificationService instance;

    private NotificationService(){
    }

    //Bill Pugh / Initialization-on-Demand Holder - Singleton design pattern
    private static class Holder{
        private static final NotificationService instance = new NotificationService();
    }

    public static NotificationService getNotificationServiceInstance(){
        return Holder.instance;
    }

    public void setObservable(NotificationObservable observable){
        this.observable = observable;
    }

    public void sendNotification(INotification notification){
        observable.setContent(notification);
        notificationsHistory.add(notification);
    }

    public void getNotificationHistoryContent(){
        for(INotification n :  notificationsHistory){
            System.out.println(n.getContent());
        }
    }

    public  List<INotification> getNotificationHistory(){
        return notificationsHistory;
    }
}

public class Main {
    public static void main(String args[]){
        System.out.println("----NOTIFICATION SYSTEM----");

        //setup notification engine
        NotificationEngine notificationEngine = new NotificationEngine();
        //create all notifications strategy were u want to be notified
        INotificationStrategy sms = new SmsNotificationStrategy("9797979393");
        INotificationStrategy email = new EmailNotificationStrategy("abc@gamil.com");
        INotificationStrategy popUp = new PopUpNotificationStrategy();
        //attach them to the observer engine
        notificationEngine.addNotificationStrategy(sms);
        notificationEngine.addNotificationStrategy(email);
        notificationEngine.addNotificationStrategy(popUp);

        //setup the observable
        NotificationObservable notificationObservable = new NotificationObservable();
        //attach observers
        notificationObservable.addObserver(new Logger());
        notificationObservable.addObserver(notificationEngine);

        //create a notification which is to be sent
        INotification notification = new SimpleNotification("The Notification System is ready.");
        //decorate them with signature and timestamp
        notification = new TimeStampNotificationDecorator(notification);
        notification = new SignatureNotificationDecorator(notification , "Mithun S");

        //create an instane of notifcication service and sent the notification;
        NotificationService service = NotificationService.getNotificationServiceInstance();
        service.setObservable(notificationObservable);
        service.sendNotification(notification);
    }
}
