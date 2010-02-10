/*
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
package com.aionemu.gameserver.world;

import java.util.HashSet;
import java.util.concurrent.Future;

import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.utils.ThreadPoolManager;

/**
 * @author MrPoke
 *
 */
public class WorldMapScriptInstance extends WorldMapInstance
{
	private final HashSet<Integer> players = new HashSet<Integer>();
	private final HashSet<VisibleObject>	allObjects	= new HashSet<VisibleObject>();
	protected Future<?> destroy;
	
	/**
	 * @param parent
	 * @param instanceId
	 */
	WorldMapScriptInstance(WorldMap parent, int instanceId)
	{
		super(parent, instanceId);
	}
	
	public boolean isInInstance(int objId)
	{
		return players.contains(objId);
	}

	public void setDestroyTime(int sec)
	{
		if (destroy != null)
			destroy.cancel(true);
		final World world = this.getWorld();
		final int instanceId = this.getInstanceId();
		final int mapId = this.getMapId();
		destroy = ThreadPoolManager.getInstance().schedule(new Runnable(){
			@Override
			public void run()
			{
				world.destroyInstance(mapId, instanceId);
			}
		}, sec*1000);
	}
	
	public void onEnter(VisibleObject object)
	{
		super.onEnter(object);
		allObjects.add(object);
	}
	
	public void addPlayer(int objId)
	{
		players.add(objId);
	}

	public void removePlayer(int objId)
	{
		players.remove(objId);
	}

	public void onLeave(VisibleObject object)
	{
		super.onLeave(object);
		allObjects.remove(object);
	}

	public void destroyInstance()
	{
		for (VisibleObject obj: allObjects)
		{
			if (obj instanceof Player)
				((Player)obj).getController().moveToBindLocation(false);
			else
			{
				obj.getController().delete();
			}
		}
	}

}
