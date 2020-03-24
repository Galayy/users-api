package com.itechart.container.spring.users.repository;

import com.itechart.container.spring.users.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Modifying
    @Query(value = "UPDATE \"user\" "
            + "SET deleted_at = :date "
            + "WHERE id = :id",
            nativeQuery = true)
    void deleteById(@Param("id") UUID userId, @Param("date") Instant date);

    @Override
    @Query(value = "SELECT * FROM \"user\" "
            + "WHERE id = :id AND deleted_at IS NULL",
            nativeQuery = true)
    Optional<UserEntity> findById(@Param("id") UUID id);

    @Query(value = "SELECT * FROM \"user\" "
            + "WHERE deleted_at IS NULL"
            + " AND login = :login",
            nativeQuery = true)
    Optional<UserEntity> findByLogin(@Param("login") String login);

    @Query(value = "SELECT * FROM \"user\" "
            + "WHERE :isDeleted = true OR (deleted_at IS NULL AND role='ROLE_USER')",
            nativeQuery = true)
    Page<UserEntity> search(@Param("isDeleted") boolean isDeleted, Pageable pageable);

    @Query(value = "SELECT CASE "
            + "WHEN EXISTS(SELECT * FROM \"user\" "
            + "WHERE deleted_at IS NULL"
            + " AND login = :login) THEN TRUE "
            + "ELSE FALSE END",
            nativeQuery = true)
    boolean existsByLogin(@Param("login") String login);

}

