package com.br.api.v1.model.input;

import com.br.api.v1.model.ModelModel;
import lombok.*;

@Getter
@Setter
public class DocumentModelInput {

	private Long documentId;
	private Long subscritorId;
	private String descricao;
    private ModelModel model;

}
