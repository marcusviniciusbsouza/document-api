package com.br.domain.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.exception.MobilNaoExisteException;
import com.br.domain.model.Mobil;
import com.br.domain.model.enums.TypeMovement;
import com.br.domain.repository.MobilRepository;
import com.br.domain.service.*;

@Service
public class MobilServiceImpl implements MobilService {
	
	@Autowired
	MobilRepository mobilRepository;
	
	@Override
	public Mobil save(Mobil mobil) {
		return mobilRepository.save(mobil);
	}

	@Override
	public Page<Mobil> filtro(Long subscritorId, Long pessoaRecebedoraId, TypeMovement typeMovement, Pageable pageable) {
		return mobilRepository.buscarMobilsFiltro(subscritorId, pessoaRecebedoraId, typeMovement, pageable);
	}

	@Override
	public Mobil buscarMobil(Long mobilId) {
		return mobilRepository.findByIdWithMovimentacoes(mobilId)
				.orElseThrow(() -> new RuntimeException("Mobil informado não existe."));
	}

	@Override
	public Mobil buscarMobil(String siglaMobil) {
		return mobilRepository.findByMobilPorSigla(siglaMobil)
				.orElseThrow(() -> new MobilNaoExisteException("O Mobil (" + siglaMobil +") informado não existe."));
	}

}
