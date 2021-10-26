package genelectrovise.magiksmostevile.network.pixiecourier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.inject.Inject;

public class MessageContent<T> {

  @Inject private static final Logger LOGGER = LogManager.getLogger(MessageContent.class);

  protected Class<T> type;
  protected T content;

  public MessageContent(Class<T> type) {
    setType(type);
  }

  public static boolean canDeserialise(JsonObject object) {

    if (object == null)
      return false;
    if (!(object instanceof JsonObject))
      return false;
    if (!object.has("type"))
      return false;
    if (!object.has("content"))
      return false;

    return true;
  }

  public static JsonObject toJson(Gson gson) {
    LOGGER.error("stub :(");
    return null;
  }

  @SuppressWarnings("unchecked")
  public static <C> MessageContent<C> fromJson(JsonObject object, Gson gson) {

    // Preliminary checks
    if (!canDeserialise(object))
      throw new IllegalArgumentException("A JsonObject's deserialisation was attempted when it was not eligible to be done. object=" + object);

    // Get class
    String typeName = "";
    Class<C> type = null;

    try {
      typeName = object.get("type").getAsString();
      type = (Class<C>) Class.forName(typeName);

    } catch (ClassNotFoundException cnf) {
      LOGGER.error("An error occurred while deserialising a " + MessageContent.class.getName() + ", trying to find a class of the name " + typeName);
      cnf.printStackTrace();

    } catch (ClassCastException cce) {
      LOGGER.error("An error occurred while deserialising a " + MessageContent.class.getName() + ", attempting to cast the class " + typeName + " to Class<C>");
      cce.printStackTrace();
    }

    // Deserialise using the type
    JsonElement jsonContent = object.get("content");
    C javaContent = gson.fromJson(jsonContent, type);

    // Format into POJO
    MessageContent<C> result = new MessageContent<>(type);
    result.setContent(javaContent);
    return result;
  }

  // Get and set

  public Class<T> getType() {
    return type;
  }

  public void setType(Class<T> type) {
    this.type = type;
  }

  public T getContent() {
    return content;
  }

  public void setContent(T content) {
    this.content = content;
  }

}
