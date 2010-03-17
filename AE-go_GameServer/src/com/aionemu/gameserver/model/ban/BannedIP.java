/**
 * This file is part of aion-emu <aion-emu.com>.
 *
 * aion-emu is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-emu is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.model.ban;

import java.sql.Timestamp;

/**
 * This class represents banned ip
 * 
 * @author SoulKeeper
 * @author Divinity - for //ban command
 */
public class BannedIP
{
   /**
    * Returns ip mask
    */
   private String      mask;

   /**
    * Returns expiration time
    */
   private Timestamp   timeEnd;

   /**
    * Checks if ban is still active
    * 
    * @return true if ban is still active
    */
   public boolean isActive()
   {
      return timeEnd == null || timeEnd.getTime() > System.currentTimeMillis();
   }

   /**
    * Retuns ip mask
    * 
    * @return ip mask
    */
   public String getMask()
   {
      return mask;
   }

   /**
    * Sets ip mask
    * 
    * @param mask
    *            ip mask
    */
   public void setMask(String mask)
   {
      this.mask = mask;
   }

   /**
    * Returns expiration time of ban
    * 
    * @return expiration time of ban
    */
   public Timestamp getTimeEnd()
   {
      return timeEnd;
   }

   /**
    * Sets expiration time of ban
    * 
    * @param timeEnd
    *            expiration time of ban
    */
   public void setTimeEnd(Timestamp timeEnd)
   {
      this.timeEnd = timeEnd;
   }

   /**
    * Returns true if this ip ban is equal to another. Based on {@link #mask}
    * 
    * @param o
    *            another ip ban
    * @return true if ban's are equals
    */
   @Override
   public boolean equals(Object o)
   {
      if (this == o)
         return true;
      if (!(o instanceof BannedIP))
         return false;

      BannedIP bannedIP = (BannedIP) o;

      return !(mask != null ? !mask.equals(bannedIP.mask) : bannedIP.mask != null);
   }

   /**
    * Returns ban's hashcode. Based on mask
    * 
    * @return ban's hashcode
    */
   @Override
   public int hashCode()
   {
      return mask != null ? mask.hashCode() : 0;
   }
}