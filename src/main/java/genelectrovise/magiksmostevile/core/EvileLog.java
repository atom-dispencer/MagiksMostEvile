/*******************************************************************************
 * Magiks Most Evile Copyright (c) 2020, 2021 GenElectrovise    
 *
 * This file is part of Magiks Most Evile.
 * Magiks Most Evile is free software: you can redistribute it and/or modify it under the terms 
 * of the GNU General Public License as published by the Free Software Foundation, 
 * either version 3 of the License, or (at your option) any later version.
 *
 * Magiks Most Evile is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE.  
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Magiks Most Evile. 
 * If not, see <https://www.gnu.org/licenses/>.
 *******************************************************************************/
package genelectrovise.magiksmostevile.core;

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
