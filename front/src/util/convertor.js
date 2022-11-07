import moment from 'moment';

export function Icon(icon) {
  return <div dangerouslySetInnerHTML={{ __html: icon }}></div>;
}

export function relTimeFormat(createdAt) {
  const changed = moment(createdAt) + 33193666;

  return moment(changed).fromNow();
}
