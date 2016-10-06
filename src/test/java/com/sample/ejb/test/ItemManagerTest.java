package com.sample.ejb.test;

import static org.junit.Assert.assertEquals;
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
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenFormatStage;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(Arquillian.class)
public class ItemManagerTest {
	

	@EJB(name = "java:jboss/exported/item-manager-ejb-test/ItemManagerImpl!com.sample.jpa.ItemRemote")
	ItemRemote helloRemote;
	private static Integer id;

	@Deployment(name="ItemManager")
	public static Archive<?> createDeployment() throws IOException {
//		JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "item-manager-testing.jar")
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
//				.addPackage(ItemRemote.class.getPackage())
////				.addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
//		return archive;
		
		return ShrinkWrap.create(WebArchive.class, "item-manager-ejb-test.war")
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
				//For EJB Remote Test
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//				.addAsResource("pom.xml", "pom.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
//		        .addAsLibraries(resolveMavenDependencies().asFile());
	}
	
//	private static MavenFormatStage resolveMavenDependencies() {
//		   return Maven.resolver()
////		            .loadPomFromFile("pom.xml")
////		            .importRuntimeDependencies()
//		            .resolve("org.jboss.shrinkwrap.resolver:shrinkwrap-resolver-api-maven:3.0.0-alpha-1","org.jboss:jboss-remote-naming:2.0.4.Final","org.jboss.xnio:xnio-nio:3.3.0.Final","org.jboss.xnio:xnio-api:3.2.2.Final")
//		            .withTransitivity();
//	}

	@Test
	public void call1_SayHelloShouldBeGetMessage() throws NamingException {
		// fail("Not yet implemented");
		assertEquals("Hello World !!!",helloRemote.sayHello());
	}
	
	@Test
	public void call2_CreateUserShouldBeGetUserId(){
//		helloRemote.getItems();
		id = helloRemote.createItem(new Item("testItem",12,43)).getId();
		assertNotNull(id);
	}
	
//	@Ignore
	@Test
	public void call3_UpdateItemShouldBeSuccess(){
//		helloRemote.getItems();
		Item item = helloRemote.getItemById(id);
		item.setName("testItem2");
		item = helloRemote.updateItem(item);
		assertEquals("testItem2",item.getName());
	}
	
//	@Ignore
	@Test
	public void call4_RemoveItemShouldBeSuccess(){
//		helloRemote.getItems();
		Item item = helloRemote.getItemById(id);
		helloRemote.removeItem(item);
		assertTrue(true);
	}
	
}
