import moment from 'moment';

function relTimeFormat(createdAt) {
  return moment(createdAt).fromNow();
}

export default relTimeFormat;
