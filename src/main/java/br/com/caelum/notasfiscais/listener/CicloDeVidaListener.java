package br.com.caelum.notasfiscais.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CicloDeVidaListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent phase) {
		System.out.println("Depois da fase " + phase);
	}

	@Override
	public void beforePhase(PhaseEvent phase) {
		System.out.println("Antes da fase " + phase);
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
