package br.com.product.commons.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.product.commons.models.FileMessage;

public interface FileMessageRepository extends CrudRepository<FileMessage, Double> {
}
