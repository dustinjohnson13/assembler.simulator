package assembler.simulator.view;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import assembler.simulator.Assembler;

@RunWith(JMock.class)
public class SimulatorViewTest {
	private final Mockery mockContext = new JUnit4Mockery() {
		{
			setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	private final Assembler mockAssembler = mockContext.mock(Assembler.class);

	// TODO: Can't currently test because headless exception occurs
	// @Test
	// public void creatingNewInstanceRegistersAsObserverOfAssembler()
	// {
	// mockContext.checking(new Expectations() {
	// {
	// oneOf(mockAssembler).registerObserver(
	// with(any(SimulatorView.class)));
	// }
	// });
	//
	// SimulatorView.newInstance(mockAssembler);
	// }

	@Test
	public void updateRemovesAllItemsFromJFrame() {
		final JFrame mockJFrame = mockContext.mock(JFrame.class);
		final Container mockContentPane = mockContext.mock(Container.class);

		mockContext.checking(new Expectations() {
			{
				allowing(mockAssembler);
				oneOf(mockJFrame).getContentPane();
				will(returnValue(mockContentPane));
				oneOf(mockContentPane).removeAll();
				allowing(mockJFrame);
				allowing(mockContentPane);
			}
		});

		SimulatorView view = new SimulatorView(mockAssembler, mockJFrame);
		view.update();
	}

	@Test
	public void updateAddsTwoPanelsToJFrame() {
		final JFrame mockJFrame = mockContext.mock(JFrame.class);
		final Container mockContentPane = mockContext.mock(Container.class);

		mockContext.checking(new Expectations() {
			{
				allowing(mockAssembler);
				oneOf(mockJFrame).getContentPane();
				will(returnValue(mockContentPane));
				exactly(2).of(mockContentPane).add(with(any(JPanel.class)));
				allowing(mockJFrame);
				allowing(mockContentPane);
			}
		});

		SimulatorView view = new SimulatorView(mockAssembler, mockJFrame);
		view.update();
	}

	@Test
	public void updatePacksJFrame() {
		final JFrame mockJFrame = mockContext.mock(JFrame.class);

		mockContext.checking(new Expectations() {
			{
				allowing(mockAssembler);
				oneOf(mockJFrame).pack();
				allowing(mockJFrame);
			}
		});

		SimulatorView view = new SimulatorView(mockAssembler, mockJFrame);
		view.update();
	}

	@Test
	public void updateSetsJFrameVisible() {
		final JFrame mockJFrame = mockContext.mock(JFrame.class);

		mockContext.checking(new Expectations() {
			{
				allowing(mockAssembler);
				oneOf(mockJFrame).setVisible(true);
				allowing(mockJFrame);
			}
		});

		SimulatorView view = new SimulatorView(mockAssembler, mockJFrame);
		view.update();
	}

}
