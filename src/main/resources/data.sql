INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('STUDENT',CURDATE(),'DBA');

INSERT INTO `person` (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`created_at`, `created_by`)
  VALUES ('Admin','admin@gmail.com','3443434343','admin', 1 ,CURDATE(),'DBA');