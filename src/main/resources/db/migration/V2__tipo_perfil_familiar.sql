-- Permite o valor FAMILIAR em usuario.tipo_perfil (PostgreSQL).
-- RENOMEIE para o próximo número livre da sua pasta db/migration (ex.: V7__tipo_perfil_familiar.sql).
-- Remove qualquer CHECK existente sobre tipo_perfil (o nome pode variar) e recria com os 3 valores.
DO $$
DECLARE c record;
BEGIN
FOR c IN
SELECT conname FROM pg_constraint
WHERE conrelid = 'usuario'::regclass
          AND contype = 'c'
          AND pg_get_constraintdef(oid) ILIKE '%tipo_perfil%'
    LOOP
        EXECUTE format('ALTER TABLE usuario DROP CONSTRAINT %I', c.conname);
END LOOP;
END $$;

ALTER TABLE usuario
    ADD CONSTRAINT usuario_tipo_perfil_check
        CHECK (tipo_perfil IN ('IDOSO', 'CUIDADOR', 'FAMILIAR'));