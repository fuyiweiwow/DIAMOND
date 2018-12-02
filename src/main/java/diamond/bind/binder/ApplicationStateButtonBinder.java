package diamond.bind.binder;


import javax.swing.AbstractButton;

import diamond.appstate.ApplicationState;
import diamond.bind.state.action.StateActionPerformer;
import diamond.paint.EditMode;
import diamond.viewsetting.ViewScreenUpdater;
import diamond.viewsetting.main.ScreenUpdater;


/**
 * A class to bind a state which holds paint action.
 * @author koji
 *
 */
public class ApplicationStateButtonBinder
	extends AbstractButtonBinder<ApplicationState<EditMode>>{




	/**
	 * This method binds a MouseListener to push the state 
	 * and an ActionListener to perform the actions of the state.
	 * 
	 * @param buttonClass
	 * @param state
	 * @param textID	A member of StringID for label
	 * @return
	 */
	@Override
	public AbstractButton createButton(
			Class<? extends AbstractButton> buttonClass,
			ApplicationState<EditMode> state, String textID){

		AbstractButton button = createEmptyButton(buttonClass, textID);

		// For catching key actions which requires immediate drawing(e.g., for catching Ctrl pressed)
		ViewScreenUpdater screenUpdater = ScreenUpdater.getInstance();
		button.addKeyListener(screenUpdater.getKeyListener());


		/*
		 * add listeners
		 */
		button.addActionListener(new StateActionPerformer(state));


		return button;
	}



}
