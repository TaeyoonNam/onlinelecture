package me.tom.onlinelecture.repository;

import me.tom.onlinelecture.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByMemberNo(Long memberNo);

}
