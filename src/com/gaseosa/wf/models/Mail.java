/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 Copyright 2014 Gabriel Franco <gabriel.franco.martinez@gmail.com>
 
 This file is part of WF Control Panel.
 
 WF Control Panel is free software: you can redistribute it and/or 
 modify it under the terms of the GNU General Public License as 
 published by the Free Software Foundation, either version 3 of the 
 License, or (at your option) any later version.
 
 WF Control Panel is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with WF Control Panel.  If not, see 
 <http://www.gnu.org/licenses/>.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package com.gaseosa.wf.models;

public class Mail {

	
	public String direccion = null;
	public String buzon = null;
	public int reenvio = 0;
	public String tema = null;
	public String mensaje = null;
	
	
	public Mail( String dir, String buz, int reen, String tem, String mens)
	{
		direccion = dir;
		buzon = buz;
		reenvio = reen;
		tema = tem;
		mensaje = mens;
		
	}
	
	public String toString()
	{
		return direccion;
	}
}
