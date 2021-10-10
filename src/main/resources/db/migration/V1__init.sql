create table todo.user (
    id         serial      primary key,
    first_name varchar     not null,
    last_name  varchar     not null,
    email      varchar     not null,
    created_at timestamptz not null default now()
);

create table todo.board (
    id         serial      primary key,
    user_id    int         not null references todo.user (id),
    title      varchar     not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table todo.column (
    id         serial      primary key,
    board_id   int         not null references todo.board (id),
    title      varchar     not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);

create table todo.ticket (
    id         serial      primary key,
    column_id  int         not null references todo.column (id),
    title      varchar     not null,
    created_at timestamptz not null default now(),
    updated_at timestamptz not null default now()
);
