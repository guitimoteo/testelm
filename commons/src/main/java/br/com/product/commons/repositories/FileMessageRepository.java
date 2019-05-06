package br.com.product.commons.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.product.commons.models.FileMessage;
import br.com.product.commons.models.FileMessage.Status;

public interface FileMessageRepository extends CrudRepository<FileMessage, Integer> {

	Status findStatusById(int id);
}
