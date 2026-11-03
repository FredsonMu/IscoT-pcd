# 04 · Como trabalhamos com Git e GitHub

Repositório: https://github.com/FredsonMu/IscoT-pcd

> O passo a passo de cada tarefa (branch → commits → push → pull request → revisão → merge) e a lista de quem faz cada classe estão em **[09-tarefas-e-git.md](09-tarefas-e-git.md)**. Este documento explica os conceitos e os comandos.

## Conceitos em 2 minutos

| Termo | O que é |
|---|---|
| repositório | a pasta do projeto + todo o seu histórico |
| commit | uma "fotografia" guardada de alterações, com mensagem |
| branch | uma linha de trabalho paralela; `main` é a principal |
| clone | copiar o repositório do GitHub para o teu PC (uma vez) |
| pull | trazer do GitHub o que o outro enviou |
| push | enviar os teus commits para o GitHub |
| pull request (PR) | pedido no GitHub para juntar uma branch à `main`; é aqui que o outro revê |
| merge | juntar uma branch noutra |
| conflito | os dois mudaram as mesmas linhas; o Git pede-nos para escolher |

## Primeira vez (cada um faz uma vez)

1. Instalar o Git (https://git-scm.com) se ainda não estiver. Testar no terminal: `git --version`.
2. Configurar o nome e email (os mesmos da conta GitHub):
   ```bash
   git config --global user.name "O teu nome"
   git config --global user.email "o-teu-email@exemplo.com"
   ```
3. O Fredson adiciona o Celso como colaborador: GitHub › repositório › Settings › Collaborators › Add people.
4. Clonar no Eclipse: **File › Import › Git › Projects from Git (with smart import) › Clone URI**, colar o URL do repositório, branch `main`, e o Eclipse importa o projeto `IscoT-pcd` sozinho.
   Alternativa pelo terminal:
   ```bash
   git clone https://github.com/FredsonMu/IscoT-pcd.git
   ```
   e depois **File › Import › General › Existing Projects into Workspace**.
5. O GitHub já não aceita a password da conta no `push`: o Git para Windows abre uma janela de login no browser na primeira vez (Git Credential Manager). No Eclipse pode ser preciso um *Personal Access Token* (GitHub › Settings › Developer settings › Tokens).

## O fluxo de todos os dias

```text
1. git pull                              (trazer o trabalho do outro)
2. git switch -c feature/nome-curto      (branch nova para a tarefa)
3. trabalhar, compilar, testar no Eclipse
4. git add <ficheiros> ; git commit -m "..."   (commits pequenos)
5. git push -u origin feature/nome-curto
6. abrir Pull Request no GitHub
7. o outro revê, comenta, aprova
8. merge para a main ; apagar a branch
```

No Eclipse, o mesmo faz-se pela vista **Git Staging** (Window › Show View › Other › Git › Git Staging) e pelo menu **Team** (botão direito no projeto): Pull, Switch To › New Branch, Commit, Push Branch.

## Regras do grupo

1. **Nunca trabalhar diretamente na `main`.** A `main` tem sempre de compilar e correr.
2. **Uma branch por tarefa**, com nome claro: `feature/topic`, `feature/actuator-frame`, `fix/stream-order`.
3. **Commits pequenos** e com mensagem no imperativo a dizer o quê e porquê:
   - bom: `Topic: espera com wait em while quando a fila está vazia`
   - mau: `alteracoes`, `update`, `funciona`
4. **Pull antes de começar** e antes de fazer push.
5. **Quem não escreveu, revê.** O PR só entra depois de o outro ler e conseguir explicar o código. É o nosso checkpoint técnico.
6. **Cada um no seu pacote** sempre que possível (ver `06-plano.md`): reduz conflitos.
7. **Não commitar `bin/`** nem ficheiros do workspace: o `.gitignore` já trata disso.
8. Antes de cada apresentação, criar uma **tag**: `git tag checkpoint-1 && git push --tags`. Assim mostramos exatamente o que foi apresentado.

## Conflitos: o que fazer

Acontece quando os dois mudam as mesmas linhas. O Git marca o ficheiro assim:

```text
<<<<<<< HEAD
a tua versão
=======
a versão do outro
>>>>>>> main
```

1. Abrir o ficheiro (no Eclipse: **Team › Merge Tool** mostra as duas versões lado a lado).
2. Decidir o que fica (às vezes, as duas partes), apagar as marcas `<<<<`, `====`, `>>>>`.
3. Compilar e testar.
4. `git add` do ficheiro e `git commit`.
5. Se houver dúvidas, ligar ao outro antes de resolver: o conflito costuma revelar que os dois tinham ideias diferentes sobre a mesma classe.

## Comandos úteis

```bash
git status                 # o que mudou e o que está preparado para commit
git log --oneline --graph  # histórico resumido
git diff                   # ver as alterações ainda não preparadas
git restore <ficheiro>     # deitar fora alterações locais de um ficheiro (cuidado!)
git switch main            # voltar à main
git branch -d feature/x    # apagar uma branch já integrada
```

## Proteger a `main` (opcional, recomendado)

GitHub › Settings › Branches › Add rule para `main` › "Require a pull request before merging" e "Require approvals: 1". Assim ninguém junta código sem o outro rever.
