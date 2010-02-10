/*
 * This file is part of aion-unique <aion-unique.com>.
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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.model.gameobjects.stats.PlayerLifeStats;
import com.aionemu.gameserver.network.aion.AionClientPacket;
import com.aionemu.gameserver.network.aion.serverpackets.SM_PLAYER_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_STATS_INFO;
import com.aionemu.gameserver.network.aion.serverpackets.SM_SYSTEM_MESSAGE;

/**
 * @author ATracer, orz, avol
 *
 */
public class CM_REVIVE extends AionClientPacket
{
	@SuppressWarnings("unused")
	private int		  worldId;
	
	/**
	 * Constructs new instance of <tt>CM_REVIVE </tt> packet
	 * @param opcode
	 */
	public CM_REVIVE(int opcode)
	{
		super(opcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl()
	{
		// empty
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl()
	{

		Player activePlayer = getConnection().getActivePlayer();

		activePlayer.setLifeStats(new PlayerLifeStats(activePlayer, activePlayer.getPlayerStatsTemplate().getMaxHp(),
		activePlayer.getPlayerStatsTemplate().getMaxMp()));
		
		activePlayer.unsetState(CreatureState.DEAD);
		
		sendPacket(SM_SYSTEM_MESSAGE.REVIVE);
		//TODO: It is not always necessary.
		//sendPacket(new SM_QUEST_LIST(activePlayer));
		sendPacket(new SM_STATS_INFO(activePlayer));
		sendPacket(new SM_PLAYER_INFO(activePlayer, true, false));

		activePlayer.getController().moveToBindLocation(true);
	}
}
