--liquibase formatted sql

--changeset spring-g17:005-move-tables-to-schemas
CREATE SCHEMA IF NOT EXISTS users;
CREATE SCHEMA IF NOT EXISTS articles;

/*ALTER TABLE public.usuario SET SCHEMA users;
ALTER TABLE public.role SET SCHEMA users;
ALTER TABLE public.usuario_role SET SCHEMA users;
ALTER TABLE public.articulo SET SCHEMA articles;*/

--rollback ALTER TABLE articles.articulo SET SCHEMA public;
--rollback ALTER TABLE users.usuario_role SET SCHEMA public;
--rollback ALTER TABLE users.role SET SCHEMA public;
--rollback ALTER TABLE users.usuario SET SCHEMA public;