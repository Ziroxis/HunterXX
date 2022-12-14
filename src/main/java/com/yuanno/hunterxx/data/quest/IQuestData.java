package com.yuanno.hunterxx.data.quest;


import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;

import java.util.List;

public interface IQuestData
{
	boolean addInProgressQuest(Quest quest);
	boolean setInProgressQuest(int slot, Quest quest);
	boolean removeInProgressQuest(Quest quest);
	boolean hasInProgressQuest(Quest quest);
	<T extends Quest> T getInProgressQuest(T quest);
	<T extends Quest> T getInProgressQuest(int slot);
	int getInProgressQuestSlot(Quest quest);
	List<Objective> getInProgressObjectives();
	Quest[] getInProgressQuests();
	void clearInProgressQuests(); 
	int countInProgressQuests();
	
	boolean addFinishedQuest(Quest quest);
	boolean removeFinishedQuest(Quest quest);
	boolean hasFinishedQuest(Quest quest);
	<T extends Quest> T getFinishedQuest(T quest);
	List<Quest> getFinishedQuests();
	void clearFinishedQuests(); 
	int countFinishedQuests();


}
