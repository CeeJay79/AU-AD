/*
 * This file is part of aion-emu <aion-emu.com>.
 *
 *  aion-emu is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-emu is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.dataholders;

import org.apache.log4j.Logger;

import com.aionemu.gameserver.dataholders.loadingutils.XmlDataLoader;

/**
 * 
 * This class is holding whole static data, that is loaded from /data/static_data directory.<br>
 * The data is loaded by XMLDataLoader using JAXB.<br>
 * <br>
 * 
 * This class temporarily also contains data loaded from txt files by DataLoaders. It'll be changed later.
 * 
 * @author Luno , orz
 * 
 */

public final class DataManager
{
	/** Logger used by this class and {@link StaticData} class */
	static Logger						log	= Logger.getLogger(DataManager.class);

	/**
	 * Npc data is keeping information about all npcs.
	 * 
	 * @see NpcData
	 */
	public final NpcData				NPC_DATA;
	
	/**
	 * Gatherable data is keeping information about all gatherables.
	 * 
	 * @see GatherableData
	 */
	public final GatherableData			GATHERABLE_DATA;

	/**
	 * Spawn data is keeping information about all spawn definitions.
	 * 
	 * @see SpawnData
	 */
	public final SpawnData				SPAWN_DATA;

	/**
	 * World maps data is keeping information about all world maps.
	 * 
	 * @see WorldMapsData
	 */
	public final WorldMapsData			WORLD_MAPS_DATA;

	/**
	 * Experience table is keeping information about experience required for each level.
	 * 
	 * @see PlayerExperienceTable
	 */
	public static TradeListData			TRADE_LIST_DATA;
	
	public static PlayerExperienceTable	PLAYER_EXPERIENCE_TABLE;

	public static TeleporterData		TELEPORTER_DATA;
	
	public static TeleLocationData		TELELOCATION_DATA;
	
	public static CubeExpandData 		CUBEEXPANDER_DATA;
	
	public static BindPointData 		BIND_POINT_DATA;
	
	public static QuestsData			QUEST_DATA;
	/**
	 * 
	 */
	public static PlayerStatsData		PLAYER_STATS_DATA;

	public static ItemData				ITEM_DATA;
	
	public static TitleData				TITLE_DATA;

	/**
	 * Player initial data table.<br />
	 * Contains initial player settings.
	 */
	public static PlayerInitialData 	PLAYER_INITIAL_DATA;
	
	/**
	 * 
	 */
	public static SkillData				SKILL_DATA;
	
	public static SkillTreeData			SKILL_TREE_DATA;
	
	/**
	 * 
	 */
	public static WalkerData			WALKER_DATA;
	
	public static ZoneData				ZONE_DATA;
	
	public GoodsListData				GOODSLIST_DATA;

	/**
	 * Constructor creating <tt>DataManager</tt> instance.<br>
	 * NOTICE: calling constructor implies loading whole data from /data/static_data immediately
	 */
	public DataManager()
	{
		log.info("##### STATIC DATA [section beginning] #####");

		

		XmlDataLoader loader = new XmlDataLoader();

		long start = System.currentTimeMillis();
		StaticData data = loader.loadStaticData();
		long time = System.currentTimeMillis() - start;

		WORLD_MAPS_DATA = data.worldMapsData;
		PLAYER_EXPERIENCE_TABLE = data.playerExperienceTable;
		PLAYER_STATS_DATA = data.statsData;
		ITEM_DATA = data.itemData;
		NPC_DATA = data.npcData;
		GATHERABLE_DATA = data.gatherableData;
		PLAYER_INITIAL_DATA = data.playerInitialData;
		SKILL_DATA = data.skillData;
		SKILL_TREE_DATA = data.skillTreeData;	
		//spawn data use NPC_DATA and GATHERABLE_DATA
		SPAWN_DATA = new SpawnData(NPC_DATA, GATHERABLE_DATA);
		TITLE_DATA = data.titleData;
		TRADE_LIST_DATA = data.tradeListData;
		TELEPORTER_DATA = data.teleporterData;	
		TELELOCATION_DATA = data.teleLocationData;	
		CUBEEXPANDER_DATA = data.cubeExpandData;
		BIND_POINT_DATA = data.bindPointData;
		QUEST_DATA = data.questData;
		ZONE_DATA = data.zoneData;	
		WALKER_DATA = data.walkerData;
		GOODSLIST_DATA = data.goodsListData;
		// some sexy time message
		long seconds = time / 1000;

		String timeMsg = seconds > 0 ? seconds + " seconds" : time + " miliseconds";

		log.info("##### [load time: " + timeMsg + "] #####");
		log.info("##### STATIC DATA [section end] #####");
	
		
	}
}