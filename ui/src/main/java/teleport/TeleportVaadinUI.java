package teleport;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.touchkit.TouchKitUI;

import com.vaadin.annotations.Theme;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.jogdial.JogDial;
import com.vaadin.jogdial.client.Position;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@TouchKitUI
@Theme("droid")
public class TeleportVaadinUI extends UI {
	private static final long serialVersionUID = 6337889226477810842L;

	@Autowired
	private DroneTemplate service;

	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
		
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
		mainLayout.setSizeFull();

		Button takeoff = new Button("Takeoff");
		takeoff.addClickListener(e -> service.takeOff());

		Button land = new Button("Land");
		land.addClickListener(e -> service.land());
		land.setClickShortcut(KeyCode.SPACEBAR);

		HorizontalLayout buttonLayout = new HorizontalLayout();
		buttonLayout.setSpacing(true);

		buttonLayout.addComponents(takeoff, land);

		JogDial rotation = new JogDial(Position.LEFT, 150);
		rotation.addAxesMoveListener(e -> service.rotateByAxis(e.getX()));

		JogDial movement = new JogDial(Position.RIGHT, 150);
		movement.addAxesMoveListener(e -> service.moveByAxis(e.getX(), e.getY()));

		HorizontalLayout jogDialLayout = new HorizontalLayout();
		jogDialLayout.setWidth(100, Unit.PERCENTAGE);
		jogDialLayout.addComponents(rotation, movement);
		jogDialLayout.setExpandRatio(movement, 1);
		jogDialLayout.setComponentAlignment(movement, Alignment.BOTTOM_RIGHT);

		mainLayout.addComponents(buttonLayout, jogDialLayout);
		mainLayout.setExpandRatio(jogDialLayout, 1);
		mainLayout.setComponentAlignment(jogDialLayout, Alignment.BOTTOM_LEFT);
		
		setContent(mainLayout);
		
		service.start();
	}
}
