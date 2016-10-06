package com.sample.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenFormatStage;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sample.entity.Item;
import com.sample.entity.User;
import com.sample.jpa.ItemLocal;
import com.sample.jpa.ItemManager;
import com.sample.jpa.ItemManagerImpl;
import com.sample.jpa.ItemRemote;
import com.sample.jpa.UserLocal;
import com.sample.jpa.UserManager;
import com.sample.jpa.UserManagerImpl;
import com.sample.jpa.UserRemote;

//@Ignore
@RunWith(Arquillian.class)
public class JoinTransactionTest {

	@EJB(name = "java:jboss/exported/join-transaction-ejb-test/ItemManagerImpl!com.sample.jpa.ItemRemote")
	ItemRemote itemService;
	
	@Deployment(name="JoinTransaction")
	public static Archive<?> createDeployment() throws IOException {
//		JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "transaction-manager-testing.jar")
//				.addClasses(Item.class)
//				.addClasses(ItemRemote.class)
//				.addClasses(ItemLocal.class)
//				.addClasses(ItemManager.class)
//				.addClasses(ItemManagerImpl.class)
//				
//				.addClasses(User.class)
//				.addClasses(UserRemote.class)
//				.addClasses(UserLocal.class)
//				.addClasses(UserManager.class)
//				.addClasses(UserManagerImpl.class)
//				// .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
//				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
//		System.out.println(archive.toString());
//		return archive;
		
		return ShrinkWrap.create(WebArchive.class, "join-transaction-ejb-test.war")
				.addClasses(Item.class)
				.addClasses(ItemRemote.class)
				.addClasses(ItemLocal.class)
				.addClasses(ItemManager.class)
				.addClasses(ItemManagerImpl.class)
				
				.addClasses(User.class)
				.addClasses(UserRemote.class)
				.addClasses(UserLocal.class)
				.addClasses(UserManager.class)
				.addClasses(UserManagerImpl.class)
//				.addClass(JoinTransactionTest.class)
				//For EJB Remote Test
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//				.addAsResource("pom.xml", "pom.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
		        .addAsLibraries(resolveMavenDependencies().asFile());
	}
	
	private static MavenFormatStage resolveMavenDependencies() {
		   return Maven.resolver()
//		            .loadPomFromFile("pom.xml")
//		            .importRuntimeDependencies()
		            .resolve("org.jboss.shrinkwrap.resolver:shrinkwrap-resolver-api-maven:3.0.0-alpha-1","org.jboss:jboss-remote-naming:2.0.4.Final","org.jboss.xnio:xnio-nio:3.3.0.Final","org.jboss.xnio:xnio-api:3.2.2.Final")
		            .withTransitivity();
	}

	@Test
	public void callItemServiceShouldBeGetHelloWorld() throws NamingException {
		assertTrue("Hello World !!!".equals(itemService.sayHello()));
	}

	@Test
	public void insertUserAndItemItemIdShouldBeNotNull() {
		Item item = new Item("testAddItem", 12, 43);
		User user = new User("testAddUser", "item", "pass");
		item = itemService.transactionTest(item, user);
		assertNotNull(item.getId());
	}

	@Ignore
	@Test(expected = RuntimeException.class)
	public void insertUserFailShouldBeGetRuntimeException() {
		Item item = new Item("testFailItem", 12, 43);
		User user = new User("testFailUser", "item", "pass");
		user.setId(1);
		itemService.transactionTest(item, user);
	}
	
}
