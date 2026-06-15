package com.hai822i;


/**
 * This class records series of times collected during a process 
 *
 * @author agutierr 
 */


import java.util.ArrayList;
import java.util.HashMap;

public class Chrono {
	private String name;
	private boolean nano;
	protected HashMap<String,Long> current_chronos=new HashMap<String,Long>();
	protected HashMap<String,ArrayList<Long>> results=new HashMap<String,ArrayList<Long>>();
	public Chrono(String name,boolean nano)
	{
		this.name=name;
		this.nano=nano;
	}
	public Chrono(String name)
	{
		this(name,false);
	}
	public String getName()
	{
		return name;
	}
	public void start(String serieName)
	{
		current_chronos.put(serieName, nano? System.nanoTime():System.currentTimeMillis());
	}
    public void stop(String serieName) {
        Long currentTime = current_chronos.get(serieName);
        if (currentTime != null) {
            ArrayList<Long> result = results.get(serieName);
            if (result == null) {
                result = new ArrayList<Long>();
                results.put(serieName, result);
            }
            // ✅ Toujours soustraire currentTime
            result.add((nano ? System.nanoTime() : System.currentTimeMillis()) - currentTime);
        }
    }

	public long getResult(String serieName)
	{
		long l=0L;
		for(Long mesure:results.get(serieName)) l+=mesure;
		return l;
	}
	public ArrayList<Long> getResultArray(String serieName)
	{
		return results.get(serieName);
	}
	public long getResult()
	{
		long l=0L;
		for(ArrayList<Long> mesures:results.values())
			for(Long mesure:mesures) l+=mesure;
		return l;
	}
	public int getSerieCount()
	{
		return results.size();
	}
	public String[] getSerieNames()
	{
		return results.keySet().toArray(new String[results.keySet().size()]);
	}
}
