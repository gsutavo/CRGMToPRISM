/**
 * <copyright>
 *
 * TAOM4E - Tool for Agent Oriented Modeling for the Eclipse Platform
 * Copyright (C) ITC-IRST, Trento, Italy
 * Authors: Mirko Morandini
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * The electronic copy of the license can be found here:
 * http://sra.itc.it/tools/taom/freesoftware/gpl.txt
 *
 * The contact information:
 * e-mail: taom4e@itc.it
 * site: http://sra.itc.it/tools/taom4e/
 *
 * </copyright>
 */

package br.unb.cic.rtgoretoprism.model.kl;

import it.itc.sra.taom4e.model.core.informalcore.formalcore.FPlan;

import java.util.ArrayList;

/**
 * A container for plan element
 * 
 * @author Mirko Morandini
 */
public class PlanContainer extends RTContainer{
	//the list of means_end goal (where this plan is a means to reach them.)
	private ArrayList<GoalContainer> meGoals;
	private ArrayList<PlanContainer> parentlist;
	
	/**
	 * Creates a new PlanContainer instance
	 * 
	 * @param p the plan to create object for 
	 */
	public PlanContainer(FPlan p) {
		super(p);
		
		meGoals = new ArrayList<GoalContainer>();
		parentlist = new ArrayList<PlanContainer>();	
		this.setCreationCondition(p.getCreationProperty());
	}

	/**
	 * Add a meansend goal to the list
	 * 
	 * @param gc the current goal container
	 */
	public void addMEGoal(GoalContainer gc) {
		meGoals.add(gc);
	}
	
	/**
	 * @param dec
	 * @return
	 */
	public PlanContainer addDecomp(PlanContainer child) {

		plans.add(child);
		if (decomposition == Const.OR || decomposition == Const.ME) {
			//mm: 'assert' commented to make ME goals possible
			// assert decomposition == Const.OR;// otherwise there is an error elsewhere!
			// for this goals dispatch-plans are created (not needed for AND-goals)
			// (needed to add more triggering goals to one dispatch plan)
			child.addParent(this);
		}
		return child;
	}

	/**
	 * Get the list of means end goal for this plan
	 * 
	 * @return Returns all goals where this plan is a means to reach them.
	 */
	public ArrayList<GoalContainer> getMEGoals() {
		return meGoals;
	}
	
	/**
	 * @param PlanContainer pc
	 */
	private void addParent(PlanContainer pc) {
		parentlist.add(pc);
	}
}