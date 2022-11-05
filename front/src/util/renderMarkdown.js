const renderToMarkdown = (content) => {
  if (
    (content.startsWith('"') && content.endsWith('"')) ||
    (content.startsWith("'") && content.endsWith("'"))
  ) {
    return content.slice(1, content.length - 2);
  }
  return content;
};

export default renderToMarkdown;
