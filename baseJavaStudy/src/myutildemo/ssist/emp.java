// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   emp.java

package myutildemo.ssist;


public class emp
{

	private int no;
	private String name;
	public int age;

	public void setNo(int i)
	{
		no = i;
	}

	public int getNo()
	{
		return no;
	}

	public void setName(String s)
	{
		name = s;
	}

	public String getName()
	{
		return name;
	}

	public String toString()
	{
		return (new StringBuilder("emp{no:")).append(no).append(",name:").append(name).append("}").toString();
	}

	public emp(int i, String s)
	{
		no = i;
		name = s;
	}

	public int add(int i, int j)
	{
		return i + j;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int i)
	{
		age = i;
	}
}
