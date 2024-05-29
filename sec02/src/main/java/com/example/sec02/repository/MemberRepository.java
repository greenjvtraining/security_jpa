package com.example.sec02.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sec02.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);

}
