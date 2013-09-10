package com.fishingbuddy.logic.Gear;

import java.util.ArrayList;
import java.util.List;

import com.fishingbuddy.logic.Gear.BaitSuperClass.BAITTYPE;

public class Gear {
	private List<Bait> bait = new ArrayList<Bait>();
	private List<HookBait> hook_bait = new ArrayList<HookBait>();
	private List <Rig> rigz = new ArrayList<Rig>();
	
	public Gear(){
		GenerateGear();
	}
	private void GenerateGear(){
		bait.add(new Bait(BAITTYPE.Boilie, "BoilieMan", 20));
		bait.add(new Bait(BAITTYPE.Boilie, "ScopexCream", 20));
		bait.add(new Bait(BAITTYPE.Boilie, "PineAppleCream", 20));
		bait.add(new Bait(BAITTYPE.Boilie, "BoilieMan", 15));
		bait.add(new Bait(BAITTYPE.Boilie, "ScopexCream", 15));
		bait.add(new Bait(BAITTYPE.Boilie, "PineAppleCream", 15));
		hook_bait.add(new HookBait(BAITTYPE.Boilie, "BoilieMan", 15));
		hook_bait.add(new HookBait(BAITTYPE.Boilie, "Scopex", 15));
		hook_bait.add(new HookBait(BAITTYPE.Boilie, "PineAppleCream", 15));
		hook_bait.add(new HookBait(BAITTYPE.POPUP, "Liver", 15));
		rigz.add(new Rig("D-rig", "Wide Gape", 6));
		rigz.add(new Rig("Chod-Rig", "Wide Gape", 6));
		rigz.add(new Rig("Stiff-Rig", "Wide Gape", 6));
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
