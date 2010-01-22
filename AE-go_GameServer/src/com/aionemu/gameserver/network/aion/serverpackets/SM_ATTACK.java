/**
 * This file is part of aion-unique <aion-unique.smfnew.com>.
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
import java.util.List;

import com.aionemu.gameserver.controllers.attack.AttackResult;
import com.aionemu.gameserver.network.aion.AionConnection;
import com.aionemu.gameserver.network.aion.AionServerPacket;

/**
 * 
 * @author -Nemesiss-
 * 
 */
public class SM_ATTACK extends AionServerPacket
{
	private int attackerobjectid;
	private int	targetObjectId;
	private int	attackno;
	private int	time;
	private int	type;
	private List<AttackResult> attackList;

	public SM_ATTACK(int attackerobjectid ,int targetObjectId, int attackno, int time, int type, List<AttackResult> attackList)
	{
		this.attackerobjectid = attackerobjectid;
		this.targetObjectId = targetObjectId;
		this.attackno = attackno;// empty
		this.time = time ;// empty
		this.type = type;// empty
		this.attackList = attackList;
	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	protected void writeImpl(AionConnection con, ByteBuffer buf)
	{		
		//attacker
		writeD(buf, attackerobjectid);
		writeC(buf, attackno); // unknown
		writeH(buf, time); // unknown
		writeC(buf, type); // unknown
		//defender
		writeD(buf, targetObjectId);
		writeC(buf, 0); //unknown
		writeC(buf, 84); // unknown
		
		switch(attackList.get(0).getAttackStatus().getId())    // Counter skills
		{
			case 4:  // case BLOCK
				writeC(buf, 32);    // Shield counter attack skill activation
				break;
			case 0:  // case DODGE
				writeC(buf, 128);    // Scout counter attack skill activation
				break;
			default:
				writeC(buf, 0);
				break;
		}

		writeC(buf, 0); // unknown

		writeC(buf, attackList.size());
		for (AttackResult attack : attackList)
		{
			writeD(buf, attack.getDamage()); // damage
			writeH(buf, attack.getAttackStatus().getId()); // attack status
		}

		writeC(buf, 0);
	}	
}
