/*
 * This file is part of aion-unique <aionunique.smfnew.com>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;
import com.aionemu.gameserver.utils.PacketSendUtility;
import com.aionemu.gameserver.world.World;
import com.aionemu.gameserver.model.gameobjects.Npc;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.templates.TeleporterTemplate;
import com.aionemu.gameserver.dataholders.DataManager;
/**
 * 
 * @author alexa026 , orz
 * 
 */
public class SM_TELEPORT extends AionServerPacket
{

	/**
	* {@inheritDoc}
	*/
	private int	targetObjectId;
	private Player	player;
	private int	type;
	private TeleporterTemplate _tele;
	public Npc npc;
	
	private static final Logger	log	= Logger.getLogger(SM_TELEPORT.class);

	
	public SM_TELEPORT(Player player, int targetObjectId)
	{
		
		this.player = player ;// empty
		this.targetObjectId = targetObjectId;
		
		World world = player.getActiveRegion().getWorld();
		 npc = (Npc) world.findAionObject(targetObjectId);
		_tele = DataManager.TELEPORTER_DATA.getTeleporterTemplate(npc.getNpcId());
		
		
	}
	
	@Override
	protected void writeImpl(AionConnection con, ByteBuffer buf)
	{	
		
		if ((_tele != null)&&(_tele.getNpcId()!=0)&&(_tele.getTeleportId()!=0))
		{
			writeD(buf, targetObjectId);
			writeD(buf, _tele.getTeleportId());

		}else
			{
			PacketSendUtility.sendMessage(player, "Missing info at npc_teleporter.xml with npcid: "+ npc.getNpcId());
			log.info(String.format("Missing teleport info with npcid: %d",npc.getNpcId()));
		
			}
	}	
}
