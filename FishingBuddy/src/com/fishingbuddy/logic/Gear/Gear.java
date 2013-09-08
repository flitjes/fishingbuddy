package com.fishingbuddy.logic.Gear;

import java.util.ArrayList;
import java.util.List;

import com.fishingbuddy.logic.Gear.BaitSuperClass.BAITTYPE;

public class Gear {
	private List<Bait> bait = new ArrayList<Bait>();
	private List<HookBait> hook_bait = new ArrayList<HookBait>();
	private List <Rig> rigz = new ArrayList<Rig>();
	
	public Gear(){
		bait.add(new Bait(BAITTYPE.Boilie, "BoilieMan", 20));
		hook_bait.add(new HookBait(BAITTYPE.Boilie, "BoilieMan", 15));
		rigz.add(new Rig("D-rig", "Wide Gape", 6));
	}

	public List<Bait> getBait() {
		return bait;
	}

	public void setBait(List<Bait> bait) {
		this.bait = bait;
	}

	public List<HookBait> getHook_bait() {
		return hook_bait;
	}

	public void setHook_bait(List<HookBait> hook_bait) {
		this.hook_bait = hook_bait;
	}

	public List<Rig> getRigz() {
		return rigz;
	}

	public void setRigz(List<Rig> rigz) {
		this.rigz = rigz;
	}
	

}
