package ut.microservices.investormicroservice.exception;
import java.util.Date;

public class ErrorDetails {
 private Date timestamp;
 private String message;
 private String details;

 public ErrorDetails(Date timestamp, String message) {
  super();
  this.timestamp = timestamp;
  this.message = message;
  this.details="Details";
 }

 public Date getTimestamp() {
  return timestamp;
 }

 public String getMessage() {
  return message;
 }

 public String getDetails() {
  return details;
 }
 public void setDetails() {
  this.details="Details";
 }
}