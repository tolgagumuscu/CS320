insert into users(userType,userName,userPassword) values (1,"BartuKaynar","112358");
insert into users(userType,userName,userPassword) values (2,"TolgaGumuscu","112358");
insert into users(userType,userName,userPassword) values (3,"FatihMemis","112358");

insert into fastfoods(foodName,foodPrice) values ("Ayvalık Tostu",7);
insert into fastfoods(foodName,foodPrice) values ("Bazlama Tost",10);
insert into fastfoods(foodName,foodPrice) values ("Kaşarlı Gözleme",12);
insert into fastfoods(foodName,foodPrice) values ("Hamburger",14);
insert into fastfoods(foodName,foodPrice) values ("Dilim Pizza",6);
insert into fastfoods(foodName,foodPrice) values ("Patates Kızartması",10);
insert into fastfoods(foodName,foodPrice) values ("Sosis Tabağı",20);
insert into fastfoods(foodName,foodPrice) values ("Kumpir",17);

update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\indir.jfif' where foodID=1;
update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\bazlamatost.jfif' where foodID=2;
update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\kasarligozleme.jfif' where foodID=3;
update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\hamburger.jfif' where foodID=4;
update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\dilim pizza.jfif' where foodID=5;
update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\patateskızartması.jfif' where foodID=6;
update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\indir (1).jfif' where foodID=7;
update fastfoods set foodImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\kumpir.jfif' where foodID=8;


insert into beverages(beverageName,beveragePrice) values ("Kola",3);
insert into beverages(beverageName,beveragePrice) values ("Ayran",3);
insert into beverages(beverageName,beveragePrice) values ("Çay",3);
insert into beverages(beverageName,beveragePrice) values ("IceTea",3);
insert into beverages(beverageName,beveragePrice) values ("Limonata",3);
insert into beverages(beverageName,beveragePrice) values ("Gazoz",3);

update beverages set beverageImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\kola.jfif' where beverageID = 1;
update beverages set beverageImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\ayran.jfif' where beverageID = 2;
update beverages set beverageImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\cay.jfif' where beverageID = 3;
update beverages set beverageImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\icetea.jfif' where beverageID = 4;
update beverages set beverageImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\limonata.jfif' where beverageID = 5;
update beverages set beverageImage = 'D:\\LECTURES\\CS\\CS320\\CS320PROJECT\\Photos\\gazoz.jfif' where beverageID = 6;

insert into computers(computerName) values ("Masa-1");
insert into computers(computerName) values ("Masa-2");
insert into computers(computerName) values ("Masa-3");
insert into computers(computerName) values ("Masa-4");
insert into computers(computerName) values ("Masa-5");
insert into computers(computerName) values ("Masa-6");
insert into computers(computerName) values ("Masa-7");
insert into computers(computerName) values ("Masa-8");
insert into computers(computerName) values ("Masa-9");
insert into computers(computerName) values ("Masa-10");
insert into computers(computerName) values ("Masa-11");
insert into computers(computerName) values ("Masa-12");
insert into computers(computerName) values ("Masa-13");
insert into computers(computerName) values ("Masa-14");
insert into computers(computerName) values ("Masa-15");
insert into computers(computerName) values ("Masa-16");
insert into computers(computerName) values ("Masa-17");
insert into computers(computerName) values ("Masa-18");
insert into computers(computerName) values ("Masa-19");
insert into computers(computerName) values ("Masa-20");
insert into computers(computerName) values ("Masa-21");
insert into computers(computerName) values ("Masa-22");
insert into computers(computerName) values ("Masa-23");
insert into computers(computerName) values ("Masa-24");
insert into computers(computerName) values ("Masa-25");


insert into orders(orderedPersonID,orderedFoodID,orderedBeverageID) values (1,3,4);
insert into orders(orderedPersonID,orderedFoodID,orderedBeverageID) values (1,3,4);





