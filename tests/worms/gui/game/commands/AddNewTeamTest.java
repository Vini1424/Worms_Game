package worms.gui.game.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import worms.gui.game.PlayGameScreen;
import worms.gui.messages.MessageType;
import worms.model.IFacade;
import worms.model.ModelException;
import worms.model.World;

public class AddNewTeamTest {
	IFacade facade = mock(IFacade.class);
	PlayGameScreen screen = mock(PlayGameScreen.class);
	World world = mock(World.class);
	AddNewTeam addNewTeam = new AddNewTeam(facade, "", screen);
	AddNewTeam ant = spy(addNewTeam);


	@Test
	public final void testCanStart() {
		assertEquals(true, ant.canStart());
	}

	@Test
	public final void testDoStartExecution() {
		doReturn(world).when(ant).getWorld();
		ant.doStartExecution();
		
		verify(facade, times(1)).addEmptyTeam(world, "");
		verify(screen, times(1)).addMessage(anyString(), eq(MessageType.NORMAL));
	}
	
/*	@Parameter
	public Exception wantedException;
	@Test(expected=ModelException.class)
	public final void testDoStartExecutionWhenErrorOccurs() {
		doReturn(world).when(ant).getWorld();
		doThrow(wantedException).when(facade).addEmptyTeam(world, "");
		
		ant.doStartExecution();
		
		
		verify(screen, times(1)).addMessage(anyString(), eq(MessageType.NORMAL));
	}*/
	
	

	@Test
	public final void testAfterExecutionCompleted() {
		ant.afterExecutionCompleted();
	}

}
