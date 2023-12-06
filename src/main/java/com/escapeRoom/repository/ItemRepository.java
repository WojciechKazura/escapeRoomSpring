package com.escapeRoom.repository;

import com.escapeRoom.entitty.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {


}
