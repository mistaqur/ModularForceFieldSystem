/*  
    Copyright (C) 2012 Thunderdark

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Contributors:
    Thunderdark - initial implementation
*/

package chb.mods.mffs.common;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;
import chb.mods.mffs.client.*;

public class CommonProxy implements IGuiHandler {
	public static final int GUI_GENERATOR = 1;
	public static final int GUI_PROJECTOR = 2;
	public static final int GUI_SECSTATION = 3;
	public static final int GUI_DEFSTATION = 4;

	public void registerRenderInformation()
{
}
	public void registerTileEntitySpecialRenderer()
{
}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te == null)
		{
			return null;
		}

		switch (ID) {
		case GUI_GENERATOR:
			return new GuiGenerator(player,
					te == null ? new TileEntityGenerator()
							: ((TileEntityGenerator) te));
		case GUI_PROJECTOR:
			return new GuiProjector(player,
					te == null ? new TileEntityProjector()
							: ((TileEntityProjector) te));
		case GUI_SECSTATION:
			return new GuiSecurityStation(player,
					te == null ? new TileEntitySecurityStation()
							: ((TileEntitySecurityStation) te));
		case GUI_DEFSTATION:
			return new GuiAreaDefenseStation(player,
					te == null ? new TileEntityAreaDefenseStation()
							: ((TileEntityAreaDefenseStation) te));
		}
		return null;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
 {			TileEntity te = world.getBlockTileEntity(x, y, z);
			if (te == null)
			{
				return null;
			}

			switch (ID) {
			case GUI_GENERATOR:
				return new ContainerGenerator(player,
						te == null ? new TileEntityGenerator()
								: ((TileEntityGenerator) te));
			case GUI_PROJECTOR:
				return new ContainerProjektor(player,
						te == null ? new TileEntityProjector()
								: ((TileEntityProjector) te));
			case GUI_SECSTATION:
				return new ContainerSecurityStation(player,
						te == null ? new TileEntitySecurityStation()
								: ((TileEntitySecurityStation) te));

			case GUI_DEFSTATION:
					return new ContainerAreaDefenseStation(player,
							te == null ? new TileEntityAreaDefenseStation()
									: ((TileEntityAreaDefenseStation) te));
			}
			return null;
		}
	}

	public World getClientWorld() {
		return null;
	}
}
