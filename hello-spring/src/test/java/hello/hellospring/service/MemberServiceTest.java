package hello.hellospring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

class MemberServiceTest {
	
	MemberService memberService;
	MemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}

	@Test
	void ȸ������() {
		// given
		Member member = new Member();
		member.setName("spring");
		
		// when
		Long saveId = memberService.join(member);
		
		// then
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void �ߺ�_ȸ��_����() {
		// given
		Member member1 = new Member();
		member1.setName("spring");

		Member member2 = new Member();
		member2.setName("spring");
		
		// when
		memberService.join(member1);
		Assertions.assertThatThrownBy(() -> memberService.join(member2)).isInstanceOf(IllegalStateException.class);
		
		try {
			memberService.join(member2);
		} catch(IllegalStateException e) {
			Assertions.assertThat(e.getMessage()).isEqualTo("�̹� �����ϴ� ȸ���Դϴ�.");
		}
		
		// then
	}

	@Test
	void findMembers() {
		
	}
	
	@Test
	void findOne() {
		
	}
}