package com.helpWanted;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.widget.Toast;

public class SoundControl implements MorseSignal
{
	private String m_MorseCode;
	private String m_MorseString;
	private MediaPlayer m_LongMediaPlayer;
	private MediaPlayer m_ShortMediaPlayer;
	private Activity m_Activity;
	private int m_Position;
	private boolean m_IsRun;

	
	public SoundControl(String str, Activity activity)
	{
		this.m_IsRun = false;
		this.m_Position = 0;
		this.m_MorseString = str;
		this.m_Activity = activity;
		this.m_LongMediaPlayer = new MediaPlayer();
		this.m_ShortMediaPlayer = new MediaPlayer();
		Morse morse = new Morse(this.m_MorseString);
		this.m_MorseCode = morse.getMorseCode();
		this.m_LongMediaPlayer = MediaPlayer.create(this.m_Activity, R.raw.longmorse);
		this.m_ShortMediaPlayer = MediaPlayer.create(this.m_Activity, R.raw.shortmorse);
		
	    try 
	    {
	    	this.m_LongMediaPlayer.prepare();
	    	this.m_ShortMediaPlayer.prepare();
		} 
	    catch (IllegalStateException e) 
	    {
			e.printStackTrace();
		} 
	    catch (IOException e)
	    {
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		if(this.m_LongMediaPlayer.isPlaying() || this.m_ShortMediaPlayer.isPlaying())
		{
			stop();
		}
		
		this.m_IsRun = true;
		doWork();
		Toast.makeText(this.m_Activity, this.m_MorseString ,Toast.LENGTH_LONG).show();
	}
	
	public void stop()
	{
		this.m_IsRun = false;
	}
	
	private void doWork()
	{	
		this.m_Position = 0;
		playSpecific(this.m_Position, getIsRun());
		
		this.m_LongMediaPlayer.setOnCompletionListener
	    (
	   		new MediaPlayer.OnCompletionListener()
	    	{	
				public void onCompletion(MediaPlayer longMediaPlayer)
				{
					playSpecific(addOne(), getIsRun());
				}
	   		}
	    );
		
		this.m_ShortMediaPlayer.setOnCompletionListener
	    (
	   		new MediaPlayer.OnCompletionListener()
	    	{
				public void onCompletion(MediaPlayer shortMediaPlayer)
				{
					playSpecific(addOne(), getIsRun());
				}
	   		}
	    );
	}
	
	private int addOne()
	{
		this.m_Position++;
		return this.m_Position;
	}
	
	private void playSpecific(int position, boolean isRun)
	{
		if(isRun == false)
		{
			return;
		}
		
		try
		{
			if(this.m_MorseCode.charAt(position) == 'a')
			{
				this.m_LongMediaPlayer.start();
			}
			else if(this.m_MorseCode.charAt(position) == 'A')
			{
				this.m_ShortMediaPlayer.start();
			}
			else
			{
				this.m_IsRun = false;
			}
		}
		catch(Exception ex)
		{
			this.m_IsRun = false;
			return;
		}

	}
	
	private boolean getIsRun()
	{
		return this.m_IsRun;
	}
	

}
