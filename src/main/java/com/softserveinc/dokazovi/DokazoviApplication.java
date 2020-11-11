package com.softserveinc.dokazovi;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import com.softserveinc.dokazovi.entity.enumerations.UserStatus;
import com.softserveinc.dokazovi.repositories.DirectionRepository;
import com.softserveinc.dokazovi.repositories.PostRepository;
import com.softserveinc.dokazovi.repositories.PostTypeRepository;
import com.softserveinc.dokazovi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DokazoviApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final PostRepository postRepository;
	private final DirectionRepository directionRepository;
	private final PostTypeRepository postTypeRepository;

	@Autowired
	public DokazoviApplication(UserRepository userRepository,
			PostRepository postRepository,
			DirectionRepository directionRepository,
			PostTypeRepository postTypeRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
		this.directionRepository = directionRepository;
		this.postTypeRepository = postTypeRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DokazoviApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		PostTypeEntity postTypeEntity = new PostTypeEntity("article");
//		DirectionEntity directionEntity = new DirectionEntity("dir1");
//		postTypeRepository.save(postTypeEntity);
//		directionRepository.save(directionEntity);
//
//		UserEntity user1 = new UserEntity("firstName", "lastName", "email", "password",
//				"qualification", "phone", "bio", UserStatus.NEW);
//		userRepository.save(user1);
//
//		PostEntity post1 = new PostEntity("old", "content", true, user1, postTypeEntity,
//				directionEntity, PostStatus.MODERATION_FIRST_SIGN);
//		postRepository.save(post1);
//
//		System.in.read();
//
//		PostEntity post2 = new PostEntity("new", "content", true, user1, postTypeEntity,
//				directionEntity, PostStatus.MODERATION_FIRST_SIGN);
//		postRepository.save(post2);
//
//		System.in.read();
//		System.out.println(postRepository.findFirstByAuthorIdOrderByCreatedAtDesc(user1.getId()));
	}
}