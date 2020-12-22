package genelectrovise.magiksmostevile.common.main;

import org.apache.logging.log4j.Logger;

public class EvileLog {
  public Logger logger;
  private boolean dev;

  /**
   * @param logger
   */
  public EvileLog(Logger logger) {
    this.logger = logger;
    this.dev = true;
  }

  public void debug(String msg) {
    logger.debug(msg);
  }

  public void info(String msg) {
    logger.info(msg);
  }

  public void warn(String msg) {
    logger.warn(msg);
  }

  public void error(String msg) {
    logger.error(msg);
  }

  public void dev(String msg) {
    if (dev) {
      logger.debug(msg);
    }
  }
}
