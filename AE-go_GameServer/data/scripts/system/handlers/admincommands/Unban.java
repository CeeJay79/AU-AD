/**
 * This file is part of aion-unique <aion-unique.org>.
 *
 * aion-unique is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-unique is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */

package admincommands;

import com.aionemu.gameserver.configs.administration.AdminConfig;
import com.aionemu.gameserver.controllers.AccountTimeController;
import com.aionemu.gameserver.controllers.BannedChatController;
import com.aionemu.gameserver.controllers.BannedIpController;
import com.aionemu.gameserver.model.account.AccountTime;
import com.aionemu.gameserver.model.ban.BannedChat;
import com.aionemu.gameserver.model.ban.BannedIP;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.utils.Util;
import com.aionemu.gameserver.utils.chathandlers.AdminCommand;
import com.aionemu.gameserver.world.World;

import com.google.inject.Inject;

import java.sql.Timestamp;

/**
 * @author Divinity
 * 
 */
public class Unban extends AdminCommand
{
   @Inject
   private World   world;
   
    public Unban()
   {
        super("unban");
    }

    @Override
    public void executeCommand(Player admin, String[] params)
   {
      String syntaxCommand = "Syntax: //unban <account | chat | ip> <character name>";
        if (admin.getAccessLevel() < AdminConfig.COMMAND_UNBAN)
        {
            PacketSendUtility.sendMessage(admin, "You dont have enough rights to execute this command");
            return;
        }

      if (params.length < 2)
      {
         PacketSendUtility.sendMessage(admin, syntaxCommand);
            return;
      }
      
      String    target = params[1];            
      
      if (params[0].equals("account"))
      {
         AccountTimeController.unban(target);
         
         // Done
         PacketSendUtility.sendMessage(admin, "INFO: The player (account) " + params[1] + " has been unbanned with successful.");
      }
      else if (params[0].equals("chat"))
      {
         BannedChatController.unban(target);
         
         // Reload all banned player
         BannedChatController.reload();
         
         // Done
         PacketSendUtility.sendMessage(admin, "INFO: The player (Chat) " + params[1] + " has been unbanned with successful.");
         
         // Warning the player
         Player    targetPlayer = world.findPlayer(Util.convertName(target));
         
         if (targetPlayer != null)
            PacketSendUtility.sendMessage(targetPlayer, "You are currently unbanned from the chat by the admin.");
      }
      else if (params[0].equals("ip"))
      {
         BannedIpController.unban(target);
         
         // Done
         PacketSendUtility.sendMessage(admin, "INFO: The player (IP) " + params[1] + " has been unbanned with successful.");
      }
      else
      {
         PacketSendUtility.sendMessage(admin, syntaxCommand);
            return;
      }
    }
}