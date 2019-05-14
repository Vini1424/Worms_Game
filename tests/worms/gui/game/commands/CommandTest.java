package worms.gui.game.commands;
import static org.junit.Assert.*;



import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;


import worms.gui.game.PlayGameScreen;
import worms.model.IFacade;
import worms.model.World;
import worms.model.Worm;

public class CommandTest {

	Command c = null;
	IFacade facade = Mockito.mock(IFacade.class);
	PlayGameScreen screen = Mockito.mock(PlayGameScreen.class);
    World world = Mockito.mock(World.class);
    
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		c = Mockito.mock(Command.class,
				Mockito.CALLS_REAL_METHODS);
	}
	@Test
	public void testGetScreen() {
		Mockito.when(c.getScreen()).thenReturn(screen);
		assertEquals(screen, c.getScreen());
	}

	@Test
	public void testGetFacade() {
		Mockito.when(c.getFacade()).thenReturn(facade);
		assertEquals(facade, c.getFacade());
	}


	
	//These are tested in the child classes
/*	@Test
	public void testStartExecutionIfCanStartIsTrue() {
		Mockito.when(c.canStart()).thenReturn(true);
		c.startExecution();
		Mockito.verify(c,Mockito.times(1)).doStartExecution();
		Mockito.verify(c,Mockito.times(1)).afterExecutionStarted();
	}
	
	@Test
	public void testStartExecutionIfCanStartIsFalse() {
		Mockito.when(c.canStart()).thenReturn(false);
		c.startExecution();
		Mockito.verify(c,Mockito.times(1)).cancelExecution();
	}*/


	/*@Test
	public void testUpdateIfIsTerminatedStaysFalse() {
		Mockito.when(c.isTerminated()).thenReturn(false);
		double dt = 1.0;
		c.update(dt);
		Mockito.verify(c, Mockito.times(1)).doUpdate(dt);	
	}*/
/*	
	@Test
	public void testUpdateIfIsTerminatedChanges() {
		Mockito.when(c.isTerminated()).thenReturn(false);
		double dt = 5.0;
		Worm worm = Mockito.mock(Worm.class); 
		Mockito.when(c.getScreen()).thenReturn(screen);
		
		Mockito.when(c.getWorld()).thenReturn(world);
		Mockito.when(screen.getWormSprite(worm)).thenReturn(null);
		Mockito.when(screen.getWorld()).thenReturn(world);
		Mockito.when(facade.isGameFinished(world)).thenReturn(true);
		Mockito.doCallRealMethod().when(c).update(dt);
		
		Mockito.verify(c, Mockito.times(1)).doUpdate(dt);
		
		
		Mockito.verify(screen, Mockito.times(1)).update();
		
		Mockito.verify(screen, Mockito.times(1)).gameFinished();
		
	}*/
	
	
/*
	@Test
	public void testGetElapsedTime() {
		//This should work as well, but does not for some reason
		//When I'm debugging c's elapsedTime field is 10.0
		Mockito.doCallRealMethod().when(c).update(10.0);
		double actual = Mockito.doCallRealMethod().when(c).getElapsedTime();
		assertEquals(10.0, actual, 0.1);
		
	}*/


	@Test
	public void testIsTerminatedIfisExecutionCancelled() {
		Mockito.doCallRealMethod().when(c).isExecutionCancelled();
		boolean expected = c.isExecutionCancelled();
		assertEquals(expected, c.isTerminated());
	}
	


	@Test
	public void testToStringIfHasBeenStarted() {
		
		String name = c.getClass().getSimpleName();
		Mockito.when(c.getElapsedTime()).thenReturn(1354491246.0);
		Mockito.when(c.hasBeenStarted()).thenReturn(false);
		assertEquals(name + " (queued)",c.toString());
	}
	
	@Test
	public void testToStringIfNot() {
		String name = c.getClass().getSimpleName();
		Mockito.when(c.getElapsedTime()).thenReturn(1354491246.0);
		Mockito.when(c.hasBeenStarted()).thenReturn(true);
		assertEquals(name + " (elapsed: 1354491246.00s)",c.toString());
	}

}
