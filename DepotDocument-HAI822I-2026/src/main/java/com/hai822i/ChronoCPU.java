/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hai822i;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;

/**
 * This class records series of times collected during a process 
 *
 * @author agutierr et marianne huchard pour startUser et stopUser
 */

public class ChronoCPU extends Chrono{
	public ChronoCPU(String name)
	{
		super(name,false);
	}
	public void start(String serieName)
	{
		current_chronos.put(serieName, getCpuTimeMillis());
	}
	synchronized public void  stop(String serieName)
	{
		Long currentTime=current_chronos.get(serieName);
		if(currentTime!=null) 
			{
			ArrayList<Long>result=results.get(serieName);
			if(result==null)
			{
				result=new ArrayList<Long>();
				results.put(serieName, result);
			}
			result.add( getCpuTimeMillis()-currentTime);
			}
	}
	public void startUser(String serieName)
	{
		current_chronos.put(serieName, getUserTimeMillis());
	}
	synchronized public void  stopUser(String serieName)
	{
		Long currentTime=current_chronos.get(serieName);
		if(currentTime!=null) 
			{
			ArrayList<Long>result=results.get(serieName);
			if(result==null)
			{
				result=new ArrayList<Long>();
				results.put(serieName, result);
			}
			result.add(getUserTimeMillis()-currentTime);
			}
	}
    /** thread CPU time in milliseconds. */
    private long getCpuTimeMillis ()
    {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        return threadMXBean.isCurrentThreadCpuTimeSupported() 
        		? threadMXBean.getCurrentThreadCpuTime()
        				/1000000: 0L;
    }
    /** thread user time in milliseconds. */
    private long getUserTimeMillis ()
    {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        return threadMXBean.isCurrentThreadCpuTimeSupported() 
        		? threadMXBean.getCurrentThreadUserTime()
        				/1000000: 0L;
    }
}
